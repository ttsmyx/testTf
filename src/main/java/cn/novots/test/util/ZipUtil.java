//package cn.novots.test.util;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.tools.zip.ZipEntry;
//import org.apache.tools.zip.ZipFile;
//import org.apache.tools.zip.ZipOutputStream;
//
//public class ZipUtil {
//    public static final String SYS_TEM_DIR = System.getProperty("java.io.tmpdir") + File.separator;
//    
//    /**
//     * 这里先不关闭流，否则会报异常
//     * @param zip      压缩目的地址
//     * @param srcFiles 压缩的源文件     
//     */
//    public static String zipFile(String zip, List<File> srcFiles) throws Exception{
//        String path = SYS_TEM_DIR + zip;
//        FileOutputStream fos = new FileOutputStream(path);
//        ZipOutputStream out = new ZipOutputStream(fos);
//        out.setEncoding("GBK");
//        for (File _f : srcFiles) {
//            handlerFile(out, _f);
//        }
//        return path;
//    }
//
//    /**
//     * 处理压缩
//     * @param out 目标文件流
//     * @param srcFile 源文件信息
//     */
//    private static void handlerFile(ZipOutputStream out, File srcFile) throws IOException {
//        InputStream in = new FileInputStream(srcFile);
//        out.putNextEntry(new ZipEntry(srcFile.getName()));
//        int len = 0;
//        byte[] _byte = new byte[1024];
//        while ((len = in.read(_byte)) > 0) {
//            out.write(_byte, 0, len);
//        }
//        in.close();
//        out.closeEntry();
//    }
//}
//
