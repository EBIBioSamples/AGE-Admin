package uk.ac.ebi.age.admin.server.mng;

import java.io.File;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

import uk.ac.ebi.age.admin.server.service.ServiceRequest;

public class RemoteRequestManager
{
 private Map<String, RemoteRequestListener> lsnrs = new TreeMap<String, RemoteRequestListener>();

 public void processUpload(ServiceRequest upReq, PrintWriter printWriter)
 {
  try
  {
   if(upReq.getHandlerName() == null)
    return;

   RemoteRequestListener lsnr = lsnrs.get(upReq.getHandlerName());

   if(lsnr != null)
   {
    lsnr.processRequest(upReq, printWriter);

    // if( lsnr.processUpload(upReq,sess,printWriter) && upReq.getFiles() !=
    // null )
    // {
    // for( File f : upReq.getFiles().values() )
    // f.delete();
    // }
   }

  }
  finally
  {
   for(File f : upReq.getFiles().values())
   {
    if( f.exists() )
     f.delete();
   }
  }
 }

 public void addRemoteRequestListener( String cmd, RemoteRequestListener ls )
 {
  lsnrs.put(cmd, ls);
 }
 
}
