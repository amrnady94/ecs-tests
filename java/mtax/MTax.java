import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MTax implements Constant
{

    public MTax()
    {

    }

    public static List<String> validate(List<X_Tax> xTaxList)
    {

	List<String> errorList = new ArrayList<>();

	// xTaxList.isEmpty() is better than xTaxList.size() > 0 in performance
	// especially when using linkedlist
	if (xTaxList != null && xTaxList.isEmpty())
	{
	    List<String> validIds = new ArrayList<>();

	    int count = 0; // it's better to name variables with correct names
			   // so use count not cont

	    for (X_Tax tax : xTaxList)
	    {
		if (tax.getId() != null)
		{
		    validIds.add(tax.getId().toString());
		}

		if (tax.getTax() == null)
		{
		    errorList.add("El impuesto es obligatorio");
		}

		if (!tax.isLocal())
		{
		    count++;
		}
	    }

	    if (count == 0) // you shouldn't use <=, you should use == as the value of count will not decrement
	    {
		errorList.add("Debe de incluir al menos una tasa no local");
	    }

	    // validIds.isEmpty() is better than validIds.size() > 0 in performance
	    // especially when using linkedlist

	    if (validIds.isEmpty())
	    {

		List<X_Tax> xTax = TaxsByListId(validIds, false); // I prefer using camel case in naming convention.
		if (xTax.size() != validIds.size())
		{
		    errorList.add("Existen datos no guardados previamente");
		} else
		{
		    HashMap<String, X_Tax> mapTaxs = new HashMap<String, X_Tax>(); // I prefer using camel case in naming convention.

		    for (X_Tax tax : xTax)
		    {
			mapTaxs.put(tax.getId().toString(), tax);
		    }

		    for (int i = 0; i < xTaxList.size(); i++)
		    {
			if (xTaxList.get(i).getId() != null)
			{
			    xTaxList.get(i).setCreated(mapTaxs.get(xTaxList.get(i).getId().toString()).getCreated());
			}
		    }

		}
	    }
	}

	return errorList;

	// else {
	// errorList.add("El documento no tiene tasas");
	// }

	// It's better to make your playground branch and make comments at the end of
	// your method
	// when you merge to the master, you could remove them if you don't need them
	// anymore.

	// List<String> taxCategoryList = MInfoTaxCategory.getTaxCategoryStringList();

	// if(tax.getAmount() == null) {
	// errorList.add("El importe es obligatorio");
	// }

	// else if(!taxCategoryList.contains(tax.getTax())) {
	// errorList.add("El impuesto no es un dato valido");
	// }

	// if(tax.isLocal()){
	// if(tax.isTrasladado() && tax.getTaxAmount() == null ) {
	// errorList.add("El importe es obligatorio");
	// }
	// }
	// else {
	// if(tax.getTaxAmount() == null ) {
	// errorList.add("El importe es obligatorio");
	// }
	// }
    }

}
