package com.bo.common.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件操作工具类
 * @author DengJinbo.
 * @Time 2017年9月20日
 */
public class FileUtils {

	/**
	 * 图片上传
	 * @param req
	 * @param folder 图片上传后保存的文件夹，多层文件夹目录参照"folder1/folder2/.."
	 * @param file 上传的图片文件
	 * @param fileName 上传的图片文件名（带后缀，例如：xxx.jpg）
	 * @param currentUserId 上传用户ID
	 * @return<br> 返回上传后图片的访问链接
	 * @author DengJinbo, 2017年9月20日.<br>
	 */
	public static String uploadImg(HttpServletRequest req, String folder, MultipartFile file, String fileName,
			long currentUserId) {
		// 项目图片访问路径，例如"http://localhost:8080/bills/images/"
		String picUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath()
				+ "/images/";
		// 文件存储物理位置，例如"D:\Server\apache-tomcat-8.0.36\webapps\bills\images\"
		String path = req.getSession().getServletContext().getRealPath("/images/" + folder);
		String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length()); // 获取文件后缀
		fileName = currentUserId + "-" + T.getNow().getTime() + suffix; // 新文件名（用户ID + 时间戳 + 文件后缀）

		File targetFolder = new File(path);
		if (!targetFolder.exists() && !targetFolder.isDirectory()) {
			targetFolder.mkdirs(); // 全路径创建, 单路径创建是：mkdir()
		}
		File targetFile = new File(targetFolder, fileName);
		try {
			file.transferTo(targetFile);
			picUrl = picUrl + folder + "/" + fileName;
		} catch (Exception e) {
			return null;
		}
		return picUrl;
	}
	
	/**
	 * 删除指定目录下除指定文件外的其他文件
	 * @param req
	 * @param folder 指定文件夹目录
	 * @param fileName<br> 指定不删除的文件的文件名
	 * @author DengJinbo, 2017年9月20日.<br>
	 */
	public static void deletImg(HttpServletRequest req, String folder, String fileName) {
		String path = req.getSession().getServletContext().getRealPath("/images/" + folder);
		File fileDirectory = new File(path);
		String[] fileList = fileDirectory.list();
		for (String file : fileList) {
			if (!file.equals(fileName)) {
				new File(path + "/" + file).delete();
			}
		}
	}
}