<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:pl='http://ujf-grenoble.fr/profileShemaHD'
                version="1.0">
   
    <xsl:output method="html"/>

    <!-- Template principal qui gere le document html -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Profile Joueur</title>
            </head>
            <body> 
                <h1 text-align="center">The Tux Game </h1>
                <p class ="profile">Profile 
                    <br/>
                    <p>
                        <b>Nom : </b>
                        <xsl:value-of select = "//name/text()"/> 
                    </p>  
                    <p> 
                        <b>Date de Naissance :  </b>
                        <xsl:value-of select="//birthday"/>
                    </p>
                    <p>
                        Vous avez Jouer <b> 
                            <xsl:value-of select="count(//games/game)" /> 
                        </b> partie(s)
                    </p>
                </p>              
                <br/>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Mot</th>
                        <th>Niveau</th>
                        <th>Temps</th>      
                    </tr>
                    <xsl:for-each select="//games/game">
                        <tr>
                            <td>
                                <xsl:value-of select="word/text()"/>
                            </td>
                            <td>
                                <xsl:value-of select="word/@level"/>
                            </td>
                            <td>
                                <xsl:value-of select="time/text()"/>
                            </td>        
                        </tr>
                    </xsl:for-each>
                   
                </table>
                
                            
                
            </body>
        </html>
    </xsl:template>


</xsl:stylesheet>
