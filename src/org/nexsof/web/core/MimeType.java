/*
 * MimeType.java (May 2013)
 *
 * Author: Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 */
package org.nexsof.web.core;

import java.util.HashMap;
import java.util.Map;

/**
 * This is to define enum/constants for the known content type.
 * The list of content type was generated from the redhat Linux /etc/mime.type file
 * This is a hash based list to identify content type from the file extension efficiently.
 *  
 * @author Mahbubur 'Sumon' Rahman (mahbubur@gmail.com)
 *
 * @version 1.0 
 */
public enum MimeType {

	// generated from /etc/mime.type
	ai ("ai","application/postscript"),
	aif ("aif","audio/x-aiff"),
	aifc ("aifc","audio/x-aiff"),
	aiff ("aiff","audio/x-aiff"),
	asc ("asc","text/plain"),
	au ("au","audio/basic"),
	avi ("avi","video/x-msvideo"),
	bcpio ("bcpio","application/x-bcpio"),
	bin ("bin","application/octet-stream"),
	bmp ("bmp","image/bmp"),
	bz2 ("bz2","application/x-bzip2"),
	cdf ("cdf","application/x-netcdf"),
	chrt ("chrt","application/x-kchart"),
	cpio ("cpio","application/x-cpio"),
	cpt ("cpt","application/mac-compactpro"),
	csh ("csh","application/x-csh"),
	css ("css","text/css"),
	dcr ("dcr","application/x-director"),
	dir ("dir","application/x-director"),
	djv ("djv","image/vnd.djvu"),
	djvu ("djvu","image/vnd.djvu"),
	dll ("dll","application/octet-stream"),
	dms ("dms","application/octet-stream"),
	doc ("doc","application/msword"),
	dvi ("dvi","application/x-dvi"),
	dxr ("dxr","application/x-director"),
	eps ("eps","application/postscript"),
	etx ("etx","text/x-setext"),
	exe ("exe","application/octet-stream"),
	ez ("ez","application/andrew-inset"),
	gif ("gif","image/gif"),
	gtar ("gtar","application/x-gtar"),
	gz ("gz","application/x-gzip"),
	hdf ("hdf","application/x-hdf"),
	hqx ("hqx","application/mac-binhex40"),
	htm ("htm","text/html"),
	html ("html","text/html"),
	ice ("ice","x-conference/x-cooltalk"),
	ief ("ief","image/ief"),
	iges ("iges","model/iges"),
	igs ("igs","model/iges"),
	jpeg ("jpeg","image/jpeg"),
	jpe ("jpe","image/jpeg"),
	jpg ("jpg","image/jpeg"),
	js ("js","application/x-javascript"),
	kar ("kar","audio/midi"),
	kil ("kil","application/x-killustrator"),
	kpr ("kpr","application/x-kpresenter"),
	kpt ("kpt","application/x-kpresenter"),
	ksp ("ksp","application/x-kspread"),
	kwd ("kwd","application/x-kword"),
	kwt ("kwt","application/x-kword"),
	latex ("latex","application/x-latex"),
	lha ("lha","application/octet-stream"),
	lzh ("lzh","application/octet-stream"),
	m3u ("m3u","audio/x-mpegurl"),
	man ("man","application/x-troff-man"),
	me ("me","application/x-troff-me"),
	mesh ("mesh","model/mesh"),
	midi ("midi","audio/midi"),
	mid ("mid","audio/midi"),
	mif ("mif","application/vnd.mif"),
	movie ("movie","video/x-sgi-movie"),
	mov ("mov","video/quicktime"),
	mp2 ("mp2","audio/mpeg"),
	mp3 ("mp3","audio/mpeg"),
	mpeg ("mpeg","video/mpeg"),
	mpe ("mpe","video/mpeg"),
	mpga ("mpga","audio/mpeg"),
	mpg ("mpg","video/mpeg"),
	msh ("msh","model/mesh"),
	ms ("ms","application/x-troff-ms"),
	mxu ("mxu","video/vnd.mpegurl"),
	nc ("nc","application/x-netcdf"),
	oda ("oda","application/oda"),
	ogg ("ogg","application/ogg"),
	pbm ("pbm","image/x-portable-bitmap"),
	pdb ("pdb","chemical/x-pdb"),
	pdf ("pdf","application/pdf"),
	pgm ("pgm","image/x-portable-graymap"),
	pgn ("pgn","application/x-chess-pgn"),
	png ("png","image/png"),
	pnm ("pnm","image/x-portable-anymap"),
	ppm ("ppm","image/x-portable-pixmap"),
	ppt ("ppt","application/vnd.ms-powerpoint"),
	ps ("ps","application/postscript"),
	qt ("qt","video/quicktime"),
	ram ("ram","audio/x-pn-realaudio"),
	ra ("ra","audio/x-realaudio"),
	ras ("ras","image/x-cmu-raster"),
	rgb ("rgb","image/x-rgb"),
	rm ("rm","audio/x-pn-realaudio"),
	roff ("roff","application/x-troff"),
	rpm ("rpm","application/x-rpm"),
	rtx ("rtx","text/richtext"),
	sgml ("sgml","text/sgml"),
	sgm ("sgm","text/sgml"),
	shar ("shar","application/x-shar"),
	sh ("sh","application/x-sh"),
	silo ("silo","model/mesh"),
	sit ("sit","application/x-stuffit"),
	skd ("skd","application/x-koan"),
	skm ("skm","application/x-koan"),
	skp ("skp","application/x-koan"),
	skt ("skt","application/x-koan"),
	smil ("smil","application/smil"),
	smi ("smi","application/smil"),
	snd ("snd","audio/basic"),
	so ("so","application/octet-stream"),
	spl ("spl","application/x-futuresplash"),
	src ("src","application/x-wais-source"),
	stc ("stc","application/vnd.sun.xml.calc.template"),
	std ("std","application/vnd.sun.xml.draw.template"),
	sti ("sti","application/vnd.sun.xml.impress.template"),
	stw ("stw","application/vnd.sun.xml.writer.template"),
	sv4cpio ("sv4cpio","application/x-sv4cpio"),
	sv4crc ("sv4crc","application/x-sv4crc"),
	swf ("swf","application/x-shockwave-flash"),
	sxc ("sxc","application/vnd.sun.xml.calc"),
	sxd ("sxd","application/vnd.sun.xml.draw"),
	sxg ("sxg","application/vnd.sun.xml.writer.global"),
	sxi ("sxi","application/vnd.sun.xml.impress"),
	sxm ("sxm","application/vnd.sun.xml.math"),
	sxw ("sxw","application/vnd.sun.xml.writer"),
	tar ("tar","application/x-tar"),
	tcl ("tcl","application/x-tcl"),
	texinfo ("texinfo","application/x-texinfo"),
	texi ("texi","application/x-texinfo"),
	tex ("tex","application/x-tex"),
	tgz ("tgz","application/x-gzip"),
	tiff ("tiff","image/tiff"),
	tif ("tif","image/tiff"),
	torrent ("torrent","application/x-bittorrent"),
	tr ("tr","application/x-troff"),
	tsv ("tsv","text/tab-separated-values"),
	t ("t","application/x-troff"),
	txt ("txt","text/plain"),
	ustar ("ustar","application/x-ustar"),
	vcd ("vcd","application/x-cdlink"),
	vrml ("vrml","model/vrml"),
	wav ("wav","audio/x-wav"),
	wbmp ("wbmp","image/vnd.wap.wbmp"),
	wbxml ("wbxml","application/vnd.wap.wbxml"),
	Web ("Web","type=application/x-java-jnlp-file"),
	wmlc ("wmlc","application/vnd.wap.wmlc"),
	wmlsc ("wmlsc","application/vnd.wap.wmlscriptc"),
	wmls ("wmls","text/vnd.wap.wmlscript"),
	wml ("wml","text/vnd.wap.wml"),
	wrl ("wrl","model/vrml"),
	xbm ("xbm","image/x-xbitmap"),
	xhtml ("xhtml","application/xhtml+xml"),
	xht ("xht","application/xhtml+xml"),
	xls ("xls","application/vnd.ms-excel"),
	xml ("xml","text/xml"),
	xpm ("xpm","image/x-xpixmap"),
	xsl ("xsl","text/xml"),
	xwd ("xwd","image/x-xwindowdump"),
	xyz ("xyz","chemical/x-xyz"),
	zip ("zip","application/zip");
	
    /** this is the file extension, used as hash key for efficient lookup*/
	private final String extension;
	
	/** This is the content type for a file extension */
    private final String contentType;

    /** This is a hash map where key,value representation as extension,content type */
    private static final Map<String, String> knownMap =
          new HashMap<String, String>();

    /** Build the MIME type hash map from the enum list of extension,content type pair */
    static {
		for (MimeType mimeType : MimeType.values()) {
		      knownMap.put(mimeType.extension, mimeType.contentType);
		}
    }
    
    /**
     *  Constructor for <code>MimeType</code> enum. This will create a extension=>content type pair.
     * 
     * @param extension this is the extension of a file
     * @param contentType this is a resource content type linked to the file's extension
     */
    MimeType(String extension, String contentType) {
	    this.extension = extension;
	    this.contentType = contentType;
    }

    /**
     * this is called with a file extension to get a content type from a known list
     * 
     * @param extension this is the extension of a file
     * @return content type based on the extension parameter of this method 
     */
    public static String getType(String extension) {
    	return knownMap.get(extension);
    }
    
    /**
     *  This is used to get HTML content type when there is any error response
     * 
     * @return this returns HTML content type
     */
    public static String getHtmlType() {
        return getType("html");
    }
}
