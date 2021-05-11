/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.networkqos;

import it.sauronsoftware.jave.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author santhosh.t
 */
public class TransCoder {
    public static void TransCode(String sourcefile,String filename,String formatname,String setCodec,Integer audioBitRate,Integer audioSamplingRate,Integer videoBitRate,int width,int height ) {
        try {
            File source=new File("E:\\NetworkQoSService\\build\\web\\videos/"+sourcefile);
                    File target=new File("E:\\NetworkQoSService\\build\\web\\videos1/"+filename+"."+formatname);
                    if(!(target.exists())) {
                    AudioAttributes aattrib=new AudioAttributes();
                    aattrib.setCodec(setCodec);
                    aattrib.setBitRate(new Integer(audioBitRate));
                    aattrib.setSamplingRate(new Integer(audioSamplingRate));
                    aattrib.setChannels(new Integer(2));
                    VideoAttributes vattrib=new VideoAttributes();
                    vattrib.setCodec("mpeg4");
                    vattrib.setBitRate(new Integer(videoBitRate));
                    vattrib.setFrameRate(new Integer(15));
                    vattrib.setSize(new VideoSize(width,height));
                    EncodingAttributes attrs = new EncodingAttributes();
                    attrs.setFormat(formatname);
                    attrs.setAudioAttributes(aattrib);
                    attrs.setVideoAttributes(vattrib);
                    Encoder encoder = new Encoder();
                    encoder.encode(source, target, attrs);
                    }
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TransCoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InputFormatException ex) {
            Logger.getLogger(TransCoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncoderException ex) {
            Logger.getLogger(TransCoder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String a[]) {
        try {
            File source=new File("E:/Wildlife.wmv");
                     File target=new File("E:/my.mp4");
                     AudioAttributes aattrib=new AudioAttributes();
                     aattrib.setCodec("libfaac");
                     aattrib.setBitRate(new Integer(256000));
                     aattrib.setSamplingRate(new Integer(65100));
                     aattrib.setChannels(new Integer(2));
                     VideoAttributes vattrib=new VideoAttributes();
                     vattrib.setCodec("mpeg4");
                     vattrib.setBitRate(new Integer(3200000));
                     vattrib.setFrameRate(new Integer(18));
                     vattrib.setSize(new VideoSize(620, 480));
                     EncodingAttributes attrs = new EncodingAttributes();
                     attrs.setFormat("mp4");
                     attrs.setAudioAttributes(aattrib);
                     attrs.setVideoAttributes(vattrib);
                     Encoder encoder = new Encoder();
                     encoder.encode(source, target, attrs);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TransCoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InputFormatException ex) {
            Logger.getLogger(TransCoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncoderException ex) {
            Logger.getLogger(TransCoder.class.getName()).log(Level.SEVERE, null, ex);
        }
		
    }
}
