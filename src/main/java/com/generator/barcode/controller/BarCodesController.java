/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.generator.barcode.controller;

import com.generator.barcode.Image.Barcode4j;
import com.generator.barcode.Image.ZXing;
import com.generator.barcode.Image.barbecue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alvaro
 */  
@Api(value = "Controlador para gerar códigos de Barra",
        description = "Controlador para gerar códigos de Barras EAN13, PDF417, CODE128 e QRCode."        
    )
@RestController
@RequestMapping("/barcodes")
public class BarCodesController {
    
    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }
    
    @ApiOperation(value = "Gerador de EAN13 com Barbecue")
    @GetMapping(value = "/barbecue/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecueEAN13Barcode(@PathVariable("barcode") String barcode)
    throws Exception {
        return ResponseEntity.ok(barbecue.generateEAN13BarcodeImage(barcode));
    }
    @ApiOperation(value = "Gerador de EAN13 com Barcode4j")
    @GetMapping(value = "/barcode4j/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barcode4jEAN13Barcode(@PathVariable("barcode") String barcode)
    throws Exception {
        return ResponseEntity.ok(Barcode4j.generateEAN13BarcodeImage(barcode));
    }
    @ApiOperation(value = "Gerador de EAN13 com ZXing")
    @GetMapping(value = "/zxing/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingEAN13Barcode(@PathVariable("barcode") String barcode)
    throws Exception {
        return ResponseEntity.ok(ZXing.generateEAN13BarcodeImage(barcode));
        
    }
    
    @ApiOperation(value = "Gerador de Code128 com ZXing")
    @GetMapping(value = "/zxing/code128/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> zxingCode128 (@PathVariable("barcode") String barcode)
    throws Exception {
        return ResponseEntity.ok(ZXing.generateCode128BarcodeImage(barcode));
    }
    
    @ApiModelProperty(name = "TEXT",allowEmptyValue = true,example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis consectetur arcu ut dolor blandit, id rhoncus elit sodales. Quisque ullamcorper neque ac odio aliquet condimentum. Vivamus mi nisi, fringilla vitae vehicula eget, ornare blandit quam. In finibus odio eget molestie semper. Quisque in tempor ex. Vestibulum hendrerit volutpat nibh non tincidunt. Vivamus id magna quis augue accumsan viverra. Donec sodales nisl lacus, sit amet venenatis nisl consectetur id. Suspendisse potenti. Donec congue elit aliquet elit gravida, ut luctus lacus porta. Interdum et malesuada fames ac ante ipsum primis in faucibus. Praesent quis hendrerit nunc, vitae dictum lectus. Donec pulvinar orci ipsum, quis molestie lorem faucibus vel. Phasellus sollicitudin eget sem ac dignissim.Sed in nisi ac turpis finibus ornare. Suspendisse eget pharetra sem. Quisque mollis blandit lectus. Sed ante nunc, euismod id erat eu, mattis vulputate erat. Morbi egestas, elit quis dapibus interdum, nulla justo tempor urna, in interdum justo ante eget erat. Proin in consequat augue. Integer fringilla at libero sit amet ultricies. Curabitur dictum in lacus sit amet tristique.Nam ultrices ex id eros rhoncus, ac dapibus orci ullamcorper. Suspendisse maximus bibendum commodo. Duis urna sem, congue non leo non, interdum sollicitudin nisl. Duis vestibulum ex neque, quis facilisis enim pellentesque a. Cras ac odio velit. Duis nec dolor nibh. Vestibulum rutrum ultrices justo, eget pretium lorem porttitor a. Quisque pulvinar consequat venenatis. Donec interdum at nisl eleifend suscipit. In vitae nisi neque. Fusce eleifend elementum tellus vitae cursus. Ut lobortis   ",
            value = "Identificador da pessoa",hidden = false)
    @ApiOperation(value = "Gerador de PDF417 com ZXing")
    @GetMapping(value = "/zxing/pdf417", produces = MediaType.IMAGE_PNG_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE )
    public ResponseEntity<BufferedImage> zxingPDF417 (@RequestBody String text)
    throws Exception {
        return ResponseEntity.ok(ZXing.generatePDF417BarcodeImage(text));
    }
    
    @ApiOperation(value = "Gerador de QRCodde com ZXing")
    @GetMapping(value = "/zxing/qrcode", produces = MediaType.IMAGE_PNG_VALUE, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<BufferedImage> zxingQRCode (@RequestBody String text)
    throws Exception {
        return ResponseEntity.ok(ZXing.generateQRCodeImage(text));
    }
    
    @ApiOperation(value = "Gerador de QRCodde com ZXing que retorna um um texto em bytecode 64 em formato json")
    @GetMapping(value = "/zxing/qrcodejson")
    public ResponseEntity<String> zxingQRCodeJson (@RequestBody String text)
    throws Exception {
        ;
        /*Primeiro, importa-se a imagem e a converte para um array de bytes*/
    BufferedImage imagem = ZXing.generateQRCodeImage8(text);
    ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
    ImageIO.write(imagem, "jpg", baos);
    Object arrayBytes = baos.toByteArray();

    /*Depois usamos a biblioteca Base64 para converter o array de bytes em uma string*/
    String encoded = Base64.getEncoder().encodeToString((byte[]) arrayBytes);

    /*Por fim, utilizamos a biblioteca JSON Simple para criar uma string no formato JSON utilizando os dados do encoded que conseguimos ao converter o array de bytes com o Base64*/

    JSONObject jo = new JSONObject();
    jo.put("imagem", encoded);
    //String jsonImagem = jo.toString();
        return ResponseEntity.ok(jo.toString());
    }
}
