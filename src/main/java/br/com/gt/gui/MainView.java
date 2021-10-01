package br.com.gt.gui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import br.com.gt.gui.pauta.FramePauta;


@Route
public class MainView extends VerticalLayout {

	public MainView() {
		add(new Text("Welcome to MainView."));
		Div menu = new Div();
	    menu.add(new RouterLink("Pauta", FramePauta.class));
		add(menu);
	}
 
}