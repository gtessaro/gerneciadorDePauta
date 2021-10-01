package br.com.gt.gui.pauta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.vaadin.klaudeta.quill.QuillEditor;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.router.Route;

import br.com.gt.exception.GenericException;
import br.com.gt.model.Pauta;

@Route("pauta")
public class FramePauta extends HorizontalLayout{

	Div divMaster =new Div();
	
	public FramePauta() {
		setMargin(true);
		setWidthFull();
		add(new Text("Cadastro de Pautas."));
		
		createDialog();
		
		Button btnNovaPauta = new Button("Nova Pauta");
		btnNovaPauta.addClickListener(event -> dialogNewPauta.open());
		btnNovaPauta.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
		Div div = new Div();
		div.add(btnNovaPauta);
		add(div);
		
		createTable();
		
		add(table);
		
	}
	
	private void createDialog() {
		QuillEditor quillEditor = new QuillEditor();
		quillEditor.getToolbarConfigurator()
		    .noFontDecorators()
		    .noColors()
		    .initEditor();

		quillEditor.setSizeFull();
//		add(quillEditor);
		
		dialogNewPauta = new Dialog();
		dialogNewPauta.add(new Text("Nova Pauta."));
		TextField titulo = new TextField("Título");
		TextArea descricao = new TextArea("Descrição");
		Button save = new Button("Salvar");
		VerticalLayout form = new VerticalLayout(titulo,descricao);
		form.expand(quillEditor);
		HorizontalLayout buttons = new HorizontalLayout(save);
	    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	    dialogNewPauta.add(form, buttons);
	    
	}
	
	private void createTable() {
		table = new TreeGrid<>();
		table.setItems(getAllPautas());
		table.addColumn(Pauta::getIdPauta).setHeader("Código");
		table.addColumn(Pauta::getTitulo).setHeader("Título");
		table.addColumn(Pauta::getDescricao).setHeader("Descrição");
		table.addColumn(Pauta::getStatus).setHeader("Status");
		table.addColumn(Pauta::getVotosSim).setHeader("Sim");
		table.addColumn(Pauta::getVotosNao).setHeader("Não");
		table.addColumn(Pauta::getSituacao).setHeader("Situação");
		table.setWidthFull();
		table.setWidth(1280, Unit.PIXELS);
		
	}
	
	
	
	public List<Pauta> getAllPautas() {

		try {
			System.out.println("Fetching all Comment objects through REST..");
			List<Pauta> pautas = new ArrayList<>();
			RestTemplate restTemplate = new RestTemplate();
			String validaAssociadoUrl = "http://localhost:8080/api/v1/pauta";
	        ResponseEntity<List<Pauta>>  resp = restTemplate.exchange(validaAssociadoUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Pauta>>(){});

	        if(resp != null && resp.hasBody()){
	        	pautas = resp.getBody();
	        }

		    System.out.println(String.format("...received %d items.", pautas.size()));

		    return pautas;
		} catch (GenericException e) {
			e.printStackTrace();
		} 
		
        return null;
	}
	
	@Override
	public void add(Component... components) {
		
		divMaster.add(components);
		
		super.add(divMaster);
	}
	
	Dialog dialogNewPauta;
	TreeGrid<Pauta> table;
	
	
}
