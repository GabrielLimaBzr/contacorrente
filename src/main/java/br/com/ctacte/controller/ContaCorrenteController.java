package br.com.ctacte.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ctacte.dto.ContaCorrenteDto;
import br.com.ctacte.services.ContaCorrenteService;


@RequestMapping(value = "/conta")
@RestController()
public class ContaCorrenteController {
	

	
	
	@Autowired
	private ContaCorrenteService service;
	

	@GetMapping(value ="/{id}")
	public ResponseEntity<ContaCorrenteDto>  findById(@PathVariable("id") Long id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	
	@GetMapping(value="/buscarConta")
	public ResponseEntity<List<ContaCorrenteDto>> buscarContaCadastro(
			@RequestBody ContaCorrenteDto filtro){
		List<ContaCorrenteDto> listaDto = service.buscarContaCadastro(filtro);
		return ResponseEntity.ok().body(listaDto);
	}
	
	@GetMapping(value="/buscarContaPag")
	public ResponseEntity<Page<ContaCorrenteDto>> buscarContaCadastroPag(
			@RequestBody ContaCorrenteDto filtro, 
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="size", defaultValue = "10") Integer size){
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<ContaCorrenteDto> listaDto = service.buscarContaCadastroPag(filtro, pageRequest);
				
		return ResponseEntity.ok().body(listaDto);
	}
	
	//localhost:8088/conta/gerarExcel/1423066
	@GetMapping("/gerarExcel/{id}")
	public ResponseEntity<Resource> gerarArquivoExcel(@PathVariable("id") Long id) throws IOException {
	    // Código para gerar o arquivo Excel aqui...
	    Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("Minha Planilha");
	    
	    // o cabeçalho
	    Row headerRow = sheet.createRow(0);
	    headerRow.createCell(0).setCellValue("IdCtacte");
	    headerRow.createCell(1).setCellValue("N.Doc");
	    headerRow.createCell(2).setCellValue("P. REF");
	    headerRow.createCell(3).setCellValue("Data Venc.");
	    headerRow.createCell(4).setCellValue("Valor");

	    // Insere os dados da lista
	    ContaCorrenteDto filtro = new ContaCorrenteDto();
	    filtro.setIdcadastro(id);
	    List<ContaCorrenteDto> registros = service.buscarContaCadastro(filtro);

	    int rowNum = 1;
	    for (ContaCorrenteDto registro : registros) {
	        Row row = sheet.createRow(rowNum++);
	        row.createCell(0).setCellValue(registro.getIdctacte());
	        row.createCell(1).setCellValue(registro.getNumDoc().toString());
	        row.createCell(2).setCellValue(registro.getPeriodoRef().toString());
	        row.createCell(3).setCellValue(registro.getDtVencimento());
	        row.createCell(4).setCellValue(registro.getValorOrigem().toString());

	    }

	    // Cria um arquivo temporário para armazenar o Excel
	    File file = File.createTempFile("minha-planilha", ".xlsx");
	    try (OutputStream outputStream = new FileOutputStream(file)) {
	        workbook.write(outputStream);
	    }

	    // Cria um recurso a partir do arquivo temporário
	    Resource resource = new FileSystemResource(file);

	    workbook.close();
	    // Retorna o arquivo Excel como um recurso para download
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=minha-planilha.xlsx")
	        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
	        .body(resource);
	}
	
}
