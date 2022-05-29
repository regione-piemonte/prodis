Generazione della libreria client
=================================

1) Scompattare prodissrvclient e modificare i path in gen_swagger.bat e package-jar.bat
2) Cancellare le directory src e build/classes
3) Eseguire gen_swagger.bat
4) Eseguire package-jar.bat
5) Modificare la classe PdfProspetto sostituendo List<byte[]> con byte[]

Per ottenere un nuovo yaml aggiornato
1) Commentare in it.csi.prodis.prodissrv.web.business.be.api.ServiziApi il metodo riceviProspettoDaSpicom
2) Commentare in it.csi.prodis.prodissrv.web.business.be.api.impl.ServiziApiServiceImpl l'Override nel metodo riceviProspettoDaSpicom:
   //@Override
3) Deployare il servizio sul wildfly locale
4) Richiamare
   http://localhost:8080/prodissrv/api/v1/swagger.yaml
   e salvare lo yaml generato in prodissrvclient/yaml
