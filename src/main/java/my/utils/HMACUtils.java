package my.utils;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 基于HMAC的消息摘要算法
 * @author taxo
 */
public class HMACUtils {

	/** 
     * 生成签名数据 
     * @param data 待加密的数据 
     * @param key  加密使用的key 
     * @return 生成MD5编码的字符串  
     * @throws InvalidKeyException 
     * @throws NoSuchAlgorithmException 
     */  
    public static String getSignature(byte[] datas, byte[] key) throws Exception{
    	SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKeySpec);
        byte[] rawHmac = mac.doFinal(datas);  
        return toHEX(rawHmac);
    }
    
    /**
     * 转换成16进制
     * @param datas
     * @return
     */
    public static String toHEX(byte[] datas){
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < datas.length; i++) {
    		byte data = datas[i];
    		int h = (data>>4) & 0x0f;
    		int l = data & 0x0f;
    		sb.append(h>9? (char)((h-10)+'a'): (char)(h + '0'));
    		sb.append(l>9? (char)((l-10)+'a'): (char)(l + '0'));
		}
    	return sb.toString();
    }
    
    
    public static String toHEX2(byte[] datas){
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < datas.length; i++) {
    		byte data = datas[i];
    		sb.append(Integer.toHexString(data));
		}
    	return sb.toString();
    }
    
    public static void main(String[] args) throws Exception {
    	byte[] datas = new String("1").getBytes();
    	byte[] key = new String("33332222232323").getBytes();
    	String out = HMACUtils.getSignature(datas, key);
    	System.out.println(out);
	}
    
    
}
