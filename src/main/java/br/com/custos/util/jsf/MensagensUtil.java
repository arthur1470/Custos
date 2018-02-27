package br.com.custos.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MensagensUtil {

	public static void info(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
	}

	public static void warn(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null));
	}
	
	public static void erro(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
	}
}
