package com.cjh.online.restaurant.alipay;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String APP_ID = "2016101500691414";

    // 商户私钥，您的PKCS8格式RSA2私钥，这些就是我们刚才设置的
    public static String MERCHANT_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCK1qRtBJPLVijLExKrW1TkuF2W+hLliE/UD/+Sh1K2BSvDZsB1Ot8qVSZTzhki/FpR/Y+zMV5WOUebe6+byA7tIG38PtNru5co/87L/1+UfVjByNQrmbP8zsBlScM/hO8Q9H10RE6CeZfN1Q4J8nNe7krbKCNVk8A/DGyi7RrmN6Cgw1DYWjImjqPZsdrYsYbUUi6Y7kK4JT61wcKwr0FeVL/YBw63zQQv1xt8E/ko+Vd0vlVWosjWhnv74hCmfZxCDdIsk1rTnxUuWYcDQrWAL8ZQ5weM8zfb+TmJm4qxMWZ1KKhx4CexHDhtL1pV0bf/joVoKRLuSbmPhX2sBUitAgMBAAECggEAaMecx/nQYIDhwIDgcho3c3YAi9SnNjsto+l/A+LE9UHpyUperRqFGbmxd0XviovQzlgCf0Ye4AvSkvFBktvlB+iVmO0BOikU54hHVYWhaLtApN8f4NfbEkwmv0pv5Bw7IBykIX77kVSa0Giec7tafqZwL/2WzRr415xU0JEI3/ytYbFhXZYE9xnEjwTz8y5C7+RIjZfuGnShK1K7lxEw/wu6bNFjqmMdBfU4wv0YcRz6I/fHFbamLLPc8Z1yO1qPueDFNQ7hhm84IlA4Ww/XZ/sMOzvQ9AvXiFHlvJBgXFMOM8ZJ5D1WPT7xcs4l40WJcc3dL39f3GpIxG6QamIAgQKBgQDz1BdlvUKxIuzWUyWurdGaANfLWvN111ENHWiCpkFeshuUEsq/yzH2clfQKNStAQk+V3DZZ7qw0R8JOlpT6jFg7ZiqapyW0G8c7k/STWU4w383sP2La5avdlj3+vysM1IWSYf+uNzr61ro5M6gKNm2XEZ9U7V8bpdsKL/yKBxq6QKBgQCRxN9pwU2xWe5jIC5erv5ef5dUOOehXhzoHkfdYPdbZOqX3HH9f4ZVGZiPK3dNvR7kYKQ8slw92ZWxC6MBMr0stf1ed8MDlh3xvcTfkhFQ+HgOUAmNxT89SJJKjOl1mod0aGPeW98Ck2XZU6snMYbLoCNUTbGuaZ2H0B1ytRwNJQKBgCvaanbe6j7HpEcL30wicP88xZMKgZ0hI6+zKVfOJRWt/O3Nz29dUVgcypRis5WGCS6QNXi0gkxI4Ecdmp9bEJR5RyrnT9CEHmfaqLqPNvRVnOzcRU2tBQX7yBaZqpYiCNJ/WRlEEF/SDaCzfkIp9rYotHjX9F0z2sIWNieMdUf5AoGAOsVMakAUife/RFn6OBSH3CPHKJ7UzNu7/d4PjXMF5+RVLSslDla3G2J5fufAbyhb5yoLrakfxZQPZVVSwC3r1bX+VHMmvjBtHEUtthIXRzUWenbMk0s+hv/Wc5ZCnjbSfB2GfmU+EbTJ8IhqTiAKv9htxhSZGb/dUp+W7+OTTr0CgYAPpnXUQreFLOL9BXfnKqIR9aFlMLOj2Pbk0sRdCmYA9TkKtN/1PD6AJbS+BIVCv4ajbj85FuBeWHkRNcUke8Q4O6quDO5gDpZ8vp7ds0fPfnx3XBjJ87KVc7sXbjGgokNsXSMuONZ8f5kuI5RwD1sGZLbzGmQKeAEV9Ye1n0Fr1g==";;

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。，这些就是我们刚才设置的
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAitakbQSTy1YoyxMSq1tU5LhdlvoS5YhP1A//kodStgUrw2bAdTrfKlUmU84ZIvxaUf2PszFeVjlHm3uvm8gO7SBt/D7Ta7uXKP/Oy/9flH1YwcjUK5mz/M7AZUnDP4TvEPR9dEROgnmXzdUOCfJzXu5K2ygjVZPAPwxsou0a5jegoMNQ2FoyJo6j2bHa2LGG1FIumO5CuCU+tcHCsK9BXlS/2AcOt80EL9cbfBP5KPlXdL5VVqLI1oZ7++IQpn2cQg3SLJNa058VLlmHA0K1gC/GUOcHjPM32/k5iZuKsTFmdSioceAnsRw4bS9aVdG3/46FaCkS7km5j4V9rAVIrQIDAQAB";


    //异步通知，再这里我们设计自己的后台代码
    public static String notify_url = "http://localhost:8081/cart_list";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8081/getTrade_no";

    // 签名方式
    public static String SIGN_TYPE = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关
    public static String GATEWAYURL = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String LOG_PATH = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(LOG_PATH + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

