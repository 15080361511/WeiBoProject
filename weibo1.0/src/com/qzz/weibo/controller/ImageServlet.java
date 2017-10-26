package com.qzz.weibo.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.qzz.weibo.util.DataUtil;

@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ����һ��DiskFileItemFactory��������
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// �õ�һ���ļ��ϴ��������ʱĿ¼
		ServletContext servletContext = this.getServletConfig()
				.getServletContext();
		File repository = (File) servletContext
				.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// ServletFileUpload ���Ķ��������ļ����ϴ�����
		ServletFileUpload upload = new ServletFileUpload(factory);

		// ����request����
		try {
			List<FileItem> items = upload.parseRequest(request);

			// List�Ĵ��� ����ʹ��foreach Ҳ�����õ�����
			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				// FileItem һ��fileItem���Ϊһ������Ԫ�ض���
				// ����֮ǰ�ı� ����item �ı��� �ļ���
				FileItem item = iter.next();

				// isFormField() ������item�Ǳ��򣨱��ʵ���Ͼ��� ���ļ��ϴ��Ĳ���,���ļ���
				if (item.isFormField()) {

					String name = item.getFieldName();
					String value = item.getString();

					System.out.println(" isFormField name :" + name + ",value"
							+ value);

				} else {
					// �ļ���Ĵ���
					String fieldName = item.getFieldName();
					String fileName = item.getName(); // �ļ���
					String contentType = item.getContentType();
					boolean isInMemory = item.isInMemory();
					long sizeInBytes = item.getSize(); // ��С
					String houzhui = fileName.substring(fileName.lastIndexOf("."));
					fileName=System.currentTimeMillis()+houzhui;
					
					// pathӦ���������ֵ�� ����ļ��ϴ�֮���ʵ��Ŀ¼������ ->��Ҫ���ļ���д����
					// ���� ʵ����Ӧ���� tomcat�µ�webapps/������/ĳ��Ŀ¼ ��ʱ��Ϊ imgs
					String path = request.getRealPath("/upload/" +fileName);
					System.out.println("path :" + path);
					// ����һ��FIle�������
					fileName = "upload/"+fileName;
					DataUtil.fileName=fileName;
					File uploadedFile = new File(path);
					// writeд ʵ�ʾ����ļ��ϴ��ľ��嶯��
					item.write(uploadedFile);
					
					PrintWriter out = response.getWriter();
					out.print("<script>parent.location.href='W_UserInfoServlet?op=updatetouxiang'</script>");
					
					System.out.println("�ϴ��ɹ�~");

				}
			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
