package helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "Ýptal");
		UIManager.put("OptionPane.noButtonText", "Hayýr");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
	}
	
	
	public static void showMsg(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch(str) {
		case "fill":
			msg = "Lütfen tün alanlarý doldurunuz";
			break;
			
		case "success":
			msg = "iþlem baþarýlý";
			break;
			
		case "error":
			msg = "Bir hata gerçekleþti";
			break;
			
		default:
			msg = str;
		}
		
		JOptionPane.showMessageDialog(null, msg,"mesaj",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static boolean confirm(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch(str) {
		
		case "sure":
			msg = "Bu iþlemi gerçekleþtirmek istiyor musunuz?";
			break;
			
		default:
			msg = str;
			break;
		}
		
		int res = JOptionPane.showConfirmDialog(null, msg, "DÝKKAT! ", JOptionPane.YES_NO_OPTION);
		if(res == 0){
			return true;
		} else {
			return false;
		}
	}
	
}
