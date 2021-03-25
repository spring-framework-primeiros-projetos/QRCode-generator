/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.generator.barcode.Image;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;

/**
 *
 * @author Alvaro
 */
public class ZXing {
    
    
    public static BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {
        EAN13Writer barcodeWriter = new EAN13Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, 300, 150);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
    
    
    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = 
          barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 400, 400);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
    
    public static BufferedImage generateCode128BarcodeImage(String barcodeText) throws Exception {
        Code128Writer  barcodeWriter = new Code128Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.CODE_128, 300, 50);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
    
    public static BufferedImage generatePDF417BarcodeImage(String barcodeText) throws Exception {
        PDF417Writer  barcodeWriter = new PDF417Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.PDF_417, 300, 50);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
    
    
}
