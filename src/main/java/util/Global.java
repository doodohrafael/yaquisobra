package util;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "globalMBean")
@ApplicationScoped
public class Global {
	
	public static Integer limparItens = 0;
}
