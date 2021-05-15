package create;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePDF {
		
    public CreatePDF() {
    	
    }
    public void Create(String numberpdf) throws IOException {
      	
    	Document document = new Document(); //�������� ������ Document
    	
    	String filepath = new File("").getCanonicalPath();
		String[] parsfilepath = filepath.split("/");
		
		int lengthpath = parsfilepath.length;
		String abspath=""; 
		for(int i=0;i<(lengthpath-1);i++) {
			abspath=abspath+parsfilepath[i]+"/";
		}
		filepath=abspath+"webapps/CreatePDF/Check.pdf";
		String imagepath=abspath+"webapps/CreatePDF/picture/ugatu.png";
		String fontpath =abspath+"/webapps/CreatePDF/fonts/times.ttf";
    	
		try {	
			PdfWriter.getInstance(document, new FileOutputStream(filepath));
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
					
		document.open(); 
		
		BaseFont times = null;
		try {
			times = BaseFont.createFont(fontpath, "cp1251", BaseFont.EMBEDDED);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
		
		String string_pdf = "������ ���� ������������� ������ �� ������� ����� ������� ��� �����! �������� ���������� ��� �������� PDF �����.";
		Paragraph paragraph = new Paragraph();
	    paragraph.add(new Paragraph(string_pdf, new Font(times,14)));
	    
	    String string_pdf2 = "�������������� �����, ������� ��������� � PDF. ��� ���� ����� ��������, ��� ����� ��������� �������� ����������, ������� ����� ��������� � ���� PDF.";
	    paragraph.add(new Paragraph(string_pdf2, new Font(times,14)));
	
	    try {
			document.add(paragraph);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
	    
	  //����������� �������� �� ��������� ������
		 paragraph.clear();
		 String string_pdf3 = " ";
		 paragraph.add(new Paragraph(string_pdf3, new Font(times,14)));
		 
		 try {
				document.add(paragraph);
			} catch (DocumentException e1) {
				e1.printStackTrace();
			}
    	
	    
	  //���������� ����������� � pdf
	    Image img = null;
		try {
			img = Image.getInstance(imagepath);
			
			
		} catch (BadElementException e2) {
			
			e2.printStackTrace();
		} catch (MalformedURLException e2) {
			
			e2.printStackTrace();
		} catch (IOException e2) {
			
			e2.printStackTrace();
		}
		
		img.setAbsolutePosition(90, 500); //���������������� ����������� � PDF
		
		try {
				document.add(img);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
	    
	    
		 //����������� �������� �� ��������� ������
		 paragraph.clear();
		 paragraph.add(new Paragraph(string_pdf3, new Font(times,14)));
		 
		 try {
				document.add(paragraph);
			} catch (DocumentException e1) {
				e1.printStackTrace();
			}
	    
		 
		//���������� �������
		 PdfPTable table = new PdfPTable(4); //�������� ������� � 4 ���������
		 addHeader(table);
		 addRows(table, times);
		 
		 try {
			document.add(table);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	    
	    document.close(); //�������� � ���������� ��������� PDF
    }
    
private void addRows(PdfPTable table,BaseFont font) {
		Phrase  phrase1 = new Phrase(Calc.NumberGet,new Font(font));
		Phrase  phrase2 = new Phrase(Calc.GroupGet,new Font(font));
		Phrase  phrase3 = new Phrase(Calc.FIOGet,new Font(font));
		Phrase  phrase4 = new Phrase(Calc.PointsGet,new Font(font));
		
		//���������� ������� ��������� �������� � ��������� ���� �� ������� �����
		String cell1 = Calc.NumberGet;
		String cell2 = Calc.GroupGet;
		String cell3 = Calc.FIOGet;
		String cell4 = Calc.PointsGet;
				
		table.addCell(phrase1);
	    table.addCell(phrase2);
	    table.addCell(phrase3);
	    table.addCell(phrase4);
		
	    //���� ������ ���� ����� �� ������� �����, ��� ��� ������� ����� ���������� � �������.
	}

private void addHeader(PdfPTable table) {
	Stream.of("Number", "Group", "FIO", "Points")
      .forEach(columnTitle -> {
        PdfPCell header = new PdfPCell();
        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
        header.setBorderWidth(2);
        header.setPhrase(new Phrase(columnTitle));
        table.addCell(header);
    });
}
}
