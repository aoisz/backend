/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.backend.service;

import com.example.backend.template.CertificateInformation;
import com.example.backend.template.CertificateScore;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.ProcessStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OcrServiceImpl implements OcrService{
    @Autowired
    private Tesseract tesseract;
    
    public String extractRawInformation(File file) {
        try {
            String text = tesseract.doOCR(file);
            return cleanData(text);
        }
        catch(TesseractException tEx) {
            return null;
        }
    }
    
    public String extractRawScore(File file) {
        try {
            tesseract.setPageSegMode(11);
            String text = tesseract.doOCR(file);
            tesseract.setPageSegMode(0);
            return cleanData(text);
        }
        catch(TesseractException tEx) {
            return null;
        }
    }
    
    public static String cleanData(String text) {
        String data = text.replace("\n", " ");
        return data;
    }
    
    public static File convert(MultipartFile file) {
        try {
            File convertFile = new File(file.getOriginalFilename());
            convertFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convertFile);
            fos.write(file.getBytes());
            fos.close();
            return convertFile;
        }
        catch (IOException e) {
            return null;
        }
    }
    
    public static String cleanString(String str) {
        try {
            str = str.replaceAll("#[\\\\s-]+#', '#[^A-Za-z0-9. -/]+#", "");
            str = URLDecoder.decode(str, StandardCharsets.UTF_8.name());
            return str;
        }
        catch(UnsupportedEncodingException e) {
            return str;
        }
    }
    
    public static String formatDate(String text) {
        String[] temp = text.split("/");
        List<String> list = Arrays.asList(temp);
        Collections.reverse(list);
        return list.stream().map(n -> String.valueOf(n)).collect(Collectors.joining("/", "", ""));
    }
    
    public static void processScoreImage(String filePath) {
        ProcessStarter.setGlobalSearchPath("C:\\Program Files\\ImageMagick-7.1.1-Q16-HDRI");
        ConvertCmd cmd = new ConvertCmd();
        IMOperation op = new IMOperation();
        op.addImage(filePath);
        op.addRawArgs("-fuzz", "50%");
        op.addRawArgs("-fill", "black");
        op.addRawArgs("-opaque", "black");
        op.addRawArgs("-bordercolor", "white");
        op.addRawArgs("-border", "2");
        op.addRawArgs("-fill", "black");
        op.addRawArgs("-draw", "\"color 0,0 floodfill\"");
        op.addRawArgs("-alpha", "off");
        op.addRawArgs("-negate");
        op.addRawArgs("-units", "pixelsperinch");
        op.addRawArgs("-density", "72");
        op.addImage(filePath);
        try {
            cmd.run(op);
        } catch (IOException | InterruptedException | IM4JavaException ex) {
            Logger.getLogger(OcrServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public File storeFile(MultipartFile file, String newFileName, String id) {
        try {
            File tempFile = new File("D:\\AllProject\\KLTN\\Store\\"+ id);
            if(!tempFile.exists()) {
                tempFile.mkdirs();
            }
            tempFile = new File(tempFile.getAbsolutePath()+ "\\" + newFileName + ".png");
            tempFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(file.getBytes());
            fos.close();
            return tempFile;
        } catch (IOException ex) {
            Logger.getLogger(OcrServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public CertificateInformation getInformation(MultipartFile file, String studentId) {
        File imageFile = storeFile(file, "information", studentId);
        String text = extractRawInformation(imageFile);
        CertificateInformation infor = new CertificateInformation();
        if(!text.isEmpty()) {
            text = text.replaceAll("\\s+", "-");
            System.out.println(text);
            String[] rawArray = text.split("-");
            String tempName = "";
            for(int i = 0; i < rawArray.length-1; i++) {
                if(!"Name".equals(rawArray[i])) {
                    tempName += " " + rawArray[i];
                }
                else {
                    infor.studentName = cleanString(tempName).trim();
                    infor.id = rawArray[i+1];
                    infor.birthDay = formatDate(rawArray[i+2]);
                }
                if(rawArray[i].equals("Test") && rawArray[i+1].equals("Date")) {
                    if(rawArray[i+2].equals("(yyyy/mm/dd)")) {
                        infor.testDate = formatDate(rawArray[i-1]);
                        infor.validUntil = formatDate(rawArray[i+3]);
                    }
                    else {
                        infor.testDate = formatDate(rawArray[i-2]);
                        infor.validUntil = formatDate(rawArray[i-1]);
                    }
                }
                if(!text.contains("Test")) {
                    if(rawArray[i].equals("Valid") && rawArray[i+1].equals("Until")) {
                        infor.testDate = formatDate(rawArray[i-2]);
                        infor.validUntil = formatDate(rawArray[i-1]);
                    }
                }
            }
            infor.imageURL = imageFile.getAbsolutePath();
        }
        return infor;
    }
    
    @Override
    public CertificateScore getScore(MultipartFile file, String studentId) {
        CertificateScore score = new CertificateScore();
        File imageFile = storeFile(file, "score", studentId);
        processScoreImage(imageFile.getAbsolutePath());
        processScoreImage(imageFile.getAbsolutePath());
        String text = extractRawScore(imageFile);
        if(!text.isBlank()) {
            try {
                text = text.replaceAll("\\s+", "-");
                text = new String(text.getBytes("UTF-8"), "ASCII");
                String[] splitArray = text.split("-");
                List<Integer> scoreArray = new ArrayList<>();
                for (String element : splitArray) {
                    element = element.replaceAll("[^0-9]", "");
                    if(StringUtils.isNumeric(element)) {
                        scoreArray.add(Integer.valueOf(element));
                    }
                }
                if(scoreArray.size() > 3) {
                    score.totalScore = Collections.max(scoreArray);
                    for(Integer num : scoreArray) {
                        if(scoreArray.contains(score.totalScore - num)) {
                            score.readingScore = num;
                            score.listeningScore = score.totalScore - num;
                        }
                    }
                    score.imageURL = imageFile.getAbsolutePath();
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(OcrServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return score;
    }
}
