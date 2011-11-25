package uk.ac.ebi.age.admin.client.ui.module.modeled;

import java.util.Collection;

import uk.ac.ebi.age.admin.client.model.AgeAbstractClassImprint;
import uk.ac.ebi.age.admin.client.ui.MetaClassDef;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class RelativesListPanel extends ListGrid
{
 private MetaClassDef metaCls;
 
 RelativesListPanel( MetaClassDef metaClassDef, Collection<? extends AgeAbstractClassImprint> nodes )
 {
  metaCls=metaClassDef;
  
  setHeight(30);
  setBodyOverflow(Overflow.VISIBLE);
  setOverflow(Overflow.VISIBLE);
  setShowHeader(false);

  ListGridField subclassIconField = new ListGridField("type", "Type", 40);
  subclassIconField.setAlign(Alignment.CENTER);
  subclassIconField.setType(ListGridFieldType.IMAGE);

  ListGridField subclassNameField = new ListGridField("name", "Class");

  setFields(subclassIconField, subclassNameField);

  if(nodes != null)
  {
   for(AgeAbstractClassImprint sc : nodes )
    addData(new ClassRecord(sc));
  }

 }
 
 public void addNode(AgeAbstractClassImprint cls)
 {
  addData(new ClassRecord(cls));
 }

 
 public void deleteNode(AgeAbstractClassImprint cls)
 {
  for(ListGridRecord rc : getRecords() )
  {
   if( ((ClassRecord)rc).getAgeAbstractClassImprint() == cls  )
   {
    removeData(rc);
    return;
   }
  }
 }


 public AgeAbstractClassImprint getSelectedClass()
 {
  ClassRecord cr = (ClassRecord)getSelectedRecord();
  
  if( cr == null )
   return null;
  
  return cr.getAgeAbstractClassImprint();
 }
 
 
 class ClassRecord extends ListGridRecord
 {
  private AgeAbstractClassImprint cls;
  
  ClassRecord( AgeAbstractClassImprint ci )
  {
   super();
   
   cls=ci;
   
   setAttribute("type", metaCls.getClassIcon(cls) );
   setAttribute("name", ci.getName() );
  }
  
  AgeAbstractClassImprint getAgeAbstractClassImprint()
  {
   return cls;
  }
 }



}