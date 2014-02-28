<?xml version="1.0"?>
<?xml-stylesheet href="chrome://browser/content/browser.css" type="text/css"?>
<?xml-stylesheet href="chrome://browser/skin/" type="text/css"?>
<%@ page contentType="application/vnd.mozilla.xul+xml" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="core" %>
<%@ taglib uri="http://xulfaces.sourceforge.net/xulfaces/jsf/xul" prefix="xf" %>
<core:view>
    <window
            xmlns:html="http://www.w3.org/1999/xhtml"
            xmlns="http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul"
            xmlns:xfc="http://xulfaces.sourceforge.net/xulfaces/xul/client.xul"
            >


        <xf:commandset>
            <xf:command id="aaa" action="#{contextController.save()}"/>
        </xf:commandset>

        <vbox flex="1">
            <groupbox flex="1">
                <xf:label value="#{mesgCtrl.message}"/>
                <xf:button label="test" command="aaa"/>
            </groupbox>
        </vbox>
        <script type="application/x-javascript" src="/aspectfaces-xul/js/xul-logger.js"/>
        <xf:bridge />
    </window>
</core:view>