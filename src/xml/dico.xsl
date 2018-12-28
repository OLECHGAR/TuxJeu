<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:dic="http://ujf-grenoble.fr/dico" 
                version="1.0">
   
    <xsl:output method="html"/>

    <!-- Template principal qui gere le document html -->
    <xsl:template match="/">
        <html>
            <head>
                <title>dico.xsl</title>
            </head>
            <body>
                <p>Liste des mots: </p>
                <xsl:apply-templates select="//dic:mot">
                    <xsl:sort select="../dic:num/text()"/> <!-- Ordre par niveau -->
                    <xsl:sort select="./text()"/> <!-- Ordre alphabetique -->
                </xsl:apply-templates>
            </body>
        </html>
    </xsl:template>


    <!-- Template que affiche chaque mot -->
    <xsl:template match="dic:mot">
        <xsl:value-of select="position()"/> : <xsl:value-of select="text()"/>
        <br/>
    </xsl:template>



</xsl:stylesheet>
