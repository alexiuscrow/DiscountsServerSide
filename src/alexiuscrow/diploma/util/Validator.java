package alexiuscrow.diploma.util;

/**
 *@deprecated Better use try/catch ;)
 */
@Deprecated
public abstract class Validator {

	public static boolean isItDouble(String str){
		try {  
			if (Validator.isItNull(str)){
				return false;
			}
		    @SuppressWarnings("unused")
			double d = Double.parseDouble(str);
		}  
		catch(NumberFormatException nfe){  
			return false;  
		}  
		return true;
	} 
	
	public static boolean isItInteger(String str){
		try {  
			if (Validator.isItNull(str)){
				return false;
			}
		    @SuppressWarnings("unused")
			int d = Integer.parseInt(str);  
		}  
		catch(NumberFormatException nfe){  
			return false;  
		}  
		return true;
	}
	
	public static boolean isItNull(Object str){
		if (str == null){
			return true;
		}
		return false;
	}
}
