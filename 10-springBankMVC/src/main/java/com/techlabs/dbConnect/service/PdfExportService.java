package com.techlabs.dbConnect.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import com.techlabs.dbConnect.dtos.TransactionDto;


@Service
public class PdfExportService {
	
	public ByteArrayInputStream exportTransactionToPdf(List<TransactionDto> transactionDto) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		PdfWriter writer = new PdfWriter(output); // start for pdf 
		PdfDocument pdfDoc = new PdfDocument(writer);
		Document document = new Document(pdfDoc);
		
		document.add(new Paragraph("Report of Transaction"));
		
		Table table = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
		// EQUAL percentage of width 5 columns and use all available width
		table.addHeaderCell("Transaction Id");
		table.addHeaderCell("Type");
		table.addHeaderCell("Amount");
		table.addHeaderCell("Sender Account");
		table.addHeaderCell("Receiver Account");
		
		for(TransactionDto transaction : transactionDto) {
			
			table.addCell(String.valueOf(transaction.getTransactionId()));
			table.addCell(transaction.getTransactionType().toString());
			table.addCell(String.valueOf(transaction.getAmount()));
			table.addCell(String.valueOf(transaction.getSenderAccountNumber()));
			table.addCell(String.valueOf(transaction.getReceiverAccountNumber()));
		}
		
		document.add(table);
		document.close();
		return new ByteArrayInputStream(output.toByteArray());
	}
	
	
}
