package trash.uk.ac.ebi.age.admin.client.model;

public enum Cardinality
{
// SINGLE ("single"),
 ANY ("any"),
 EXACT ("exactly"),
 MAX ("max"),
 MIN ("min");

 private String title;
 
 Cardinality(String tl)
 {
  title=tl;
 }
 
 public String getTitle()
 {
  return title;
 }
}
