<%-- 
    Document   : co
    Created on : Apr 11, 2014, 4:55:55 PM
    Author     : joaosantos
--%>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <html xmlns="http://www.w3.org/1999/xhtml">
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <script language="javascript" type="text/javascript">
 function dropdownlist(listindex)
 {
 
document.formname.subcategory.options.length = 0;
 switch (listindex)
 {
 
 case "Home Ware" :
 document.formname.subcategory.options[0]=new Option("Select Sub-Category","");
 document.formname.subcategory.options[1]=new Option("Air-Conditioners/Coolers","Air-Conditioners/Coolers");
 document.formname.subcategory.options[2]=new Option("Audio/Video","Audio/Video");
 document.formname.subcategory.options[3]=new Option("Beddings","Beddings");
 document.formname.subcategory.options[4]=new Option("Camera","Camera");
 document.formname.subcategory.options[5]=new Option("Cell Phones","Cell Phones");
 
 break;
 
 case "Education" :
 document.formname.subcategory.options[0]=new Option("Select Sub-Category","");
 document.formname.subcategory.options[1]=new Option("Colleges","Colleges");
 document.formname.subcategory.options[2]=new Option("Institutes","Institutes");
 document.formname.subcategory.options[3]=new Option("Schools","Schools");
 document.formname.subcategory.options[4]=new Option("Tuitions","Tuitions");
 document.formname.subcategory.options[5]=new Option("Universities","Universities");
 
 break;
 
 case "Books" :
 document.formname.subcategory.options[0]=new Option("Select Sub-Category","");
 document.formname.subcategory.options[1]=new Option("College Books","College Books");
 document.formname.subcategory.options[2]=new Option("Engineering","Engineering");
 document.formname.subcategory.options[3]=new Option("Magazines","Magazines");
 document.formname.subcategory.options[4]=new Option("Medicine","Medicine");
 document.formname.subcategory.options[5]=new Option("References","References");
 
 break;
 
 }
 return true;
 }
 </script>
 </head>
 <title>Dynamic Drop Down List</title>
 <body>
 
<form id="formname" name="formname" method="post" action="submitform.asp" >
 <table width="50%" border="0" cellspacing="0" cellpadding="5">
 <tr>
 <td width="41%" align="right" valign="middle">Category :</td>
 <td width="59%" align="left" valign="middle"><select name="category" id="category" onchange="javascript: dropdownlist(this.options[this.selectedIndex].value);">
 <option value="">Select Category</option>
 <option value="Home Ware">Home Ware</option>
 <option value="Education">Education</option>
 <option value="Books">Books</option>
 </select></td>
 </tr>
 <tr>
 <td align="right" valign="middle">Sub Category :
 </td>
 <td align="left" valign="middle"><script type="text/javascript" language="JavaScript">
 document.write('<select name="subcategory"><option value="">Select Sub-Category</option></select>')
 </script>
 <noscript><select name="subcategory" id="subcategory" >
 <option value="">Select Sub-Category</option>
 </select>
 </noscript></td>
 </tr>
 </table>
 
</form>
 
 
</body>
 </html>
