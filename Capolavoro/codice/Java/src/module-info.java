module prova1 {
	exports view;
	exports main;

	requires java.desktop;
	requires java.xml;
	requires com.sun.xml.bind;
	requires jakarta.xml.bind;
	
	opens model;
	opens view;
}