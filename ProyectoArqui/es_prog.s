* Inicializa el SP y el PC
**************************
        ORG     $0
        DC.L    $8000           * Pila
        DC.L    INICIO          * PC

        ORG     $400

* Definición de equivalencias
*********************************

MR1A    EQU     $effc01       * de modo A (escritura)
MR2A    EQU     $effc01       * de modo A (2º escritura)
SRA     EQU     $effc03       * de estado A (lectura)
CSRA    EQU     $effc03       * de seleccion de reloj A (escritura)
CRA     EQU     $effc05       * de control A (escritura)
TBA     EQU     $effc07       * buffer transmision A (escritura)
RBA     EQU     $effc07       * buffer recepcion A  (lectura)
ACR	EQU	$effc09	      * de control auxiliar
IMR     EQU     $effc0B       * de mascara de interrupcion A (escritura)
ISR     EQU     $effc0B       * de estado de interrupcion A (lectura)
MR1B    EQU     $effc11       * de modo B (escritura)
MR2B    EQU     $effc11       * de modo B (2º escritura)
CRB     EQU     $effc15	      * de control A (escritura)
TBB     EQU     $effc17       * buffer transmision B (escritura)
RBB	EQU	$effc17       * buffer recepcion B (lectura)
SRB     EQU     $effc13       * de estado B (lectura)
CSRB	EQU	$effc13       * de seleccion de reloj B (escritura)
IVR     EQU     $effc19       * de vector de interrupcion 

CR	EQU	$0D	      * Carriage Return
LF	EQU	$0A	      * Line Feed
FLAGT	EQU	2	      * Flag de transmisión
FLAGR   EQU     0	      * Flag de recepción



**************************** INIT *************************************************************
INIT:
        MOVE.B          #%00010000,CRA      * Reinicia el puntero MR1
        MOVE.B          #%00010000,CRB      * Reinicia el puntero MR2
        MOVE.B          #%00000011,MR1A     * 8 bits por caracter.
        MOVE.B          #%00000011,MR1B     * 8 bits por caracter.
        MOVE.B          #%00000000,MR2A     * Eco desactivado.
        MOVE.B          #%00000000,MR2B     * Eco desactivado.
        MOVE.B          #%11001100,CSRA     * Velocidad = 38400 bps.
        MOVE.B          #%11001100,CSRB     * Velocidad = 38400 bps.
        MOVE.B          #%00000000,ACR      * Velocidad = 38400 bps.
        MOVE.B          #%00100010,IMR      * Habilitar interrupciones
        MOVE.B          #$40,IVR            * Establecer vector de interrucpcion a 40
        MOVE.B          #%00000101,CRA      * Transmision y recepcion activados.
        MOVE.B          #%00000101,CRB      * Transmision y recepcion activados.
        MOVE.B          IMR,D5              * Guardamos una copia de IMR en D5

        BSR             INI_BUFS            *Llamamos a la subrutina para inicializar los buffers

        MOVE.L          #RTI,256

        RTS
**************************** FIN INIT *********************************************************
**************************** SCAN ************************************************************
SCAN: 
        MOVEM.L A0/D0-D3,-(A7)
        MOVE.L 24(A7),A0
        CLR.W D1
        CLR.W D2
        CLR.L D3
        MOVE.W 28(A7),D1
        MOVE.W 30(A7),D2
        CMP.W #0,D1
        BEQ ASCAN
        CMP.W #1,D1
        BEQ BSCAN
        MOVE.L #$FFFFFFFF,D0
        BRA FINSCAN
ASCAN: 
        CLR.L D0
        BSR LEECAR
        CMP.L #$FFFFFFFF,D0
        BEQ COPIACONTS
        MOVE.B D0,(A0)+ 
        ADD.L #1,D3
        SUB.W #1,D2  
        BNE ASCAN 
        BRA COPIACONTS
BSCAN: 
        CLR.L D0
        MOVE.L #1,D0
        BSR LEECAR
        CMP.L #$FFFFFFFF,D0
        BEQ COPIACONTS
        MOVE.B D0,(A0)+ 
        ADD.L #1,D3
        SUB.W #1,D2  
        BNE BSCAN 
        BRA COPIACONTS     
COPIACONTS: MOVE.L D3,D0
FINSCAN:  
        MOVEM.L (A7)+,A0/D0-D3
        RTS                              

**************************** FIN SCAN ******************************************

**************************** PRINT ************************************************************
PRINT:  
        MOVEM.L A0/D0-D4,-(A7)
        MOVE.L 28(A7),A0
        CLR.B D1
        CLR.W D2
        CLR.W D3
        CLR.L D4
        MOVE.W 32(A7),D3
        MOVE.W 34(A7),D2
        CMP.W #0,D3
        BEQ APRINT
        CMP.W #1,D3
        BEQ BPRINT
        MOVE.L #$FFFFFFFF,D0
        BRA FINPRINT
APRINT:
        CLR.L D0
        MOVE.L #2,D0    
        CMP.W #0,D2
        BEQ HABINT
        MOVE.B D1,(A0)+
        BSR ESCCAR
        CMP.L #$FFFFFFFF,D0
        BEQ HABINT
        ADD.L #1,D4
        SUB.W #1,D2 
        BRA APRINT
BPRINT: 
        CLR.L D0
        MOVE.L #3,D0 
        CMP.W #0,D2
        BEQ HABINT
        MOVE.B D1,(A0)+
        BSR ESCCAR
        CMP.L #$FFFFFFFF,D0
        BEQ HABINT
        ADD.L #1,D4
        SUB.W #1,D2 
        BRA BPRINT    
HABINT: 
        MOVE.W SR,D7
        MOVE.W #$2700,SR
        CMP #0,D3
        BNE INTB
INTA:
        BSET #0,D5
        MOVE.B D5,IMR
        BRA COPIA
INTB:     
        BSET #4,D5
        MOVE.B D5,IMR      
COPIA:  
        MOVE.W D7,SR
        MOVE.L D4,D0
FINPRINT: 
        MOVEM.L (A7)+,A0/D0-D4  
        RTS                                 
**************************** FIN PRINT ********************************************************
**************************** RTI ************************************************************
RTI: 
        MOVEM.L D0-D2,-(A7)
INIRTI: 
        MOVE.B ISR,D2
        AND.B D5,D2
        BTST #0,D2
        BNE TXRDYA *INTERRUPCION TRANSMISION LINEA A
        BTST #1,D2
        BNE  RXRDYA *INTERRUPCION RECEPCION LINEA A
        BTST #4,D2
        BNE TXRDYB *INTERRUPCION TRANSMISION LINEA B
        BTST #5,D2
        BNE RXRDYB *INTERRUPCION RECEPCION LINEA B
        BRA FINRTI
TXRDYA:
        MOVE.L #2,D0 
        BSR LEECAR
        CMP.L #$FFFFFFFF,D0
        BEQ NOINTA
        MOVE.B D0,TBA
        BRA INIRTI
RXRDYA: 
        CLR.L D0
        MOVE.B RBA,D1
        BSR ESCCAR
        CMP.L #$FFFFFFFF,D0
        BEQ FINRTI
        BRA INIRTI
TXRDYB:
        MOVE.L #3,D0 
        BSR LEECAR
        CMP.L #$FFFFFFFF,D0
        BEQ NOINTB
        MOVE.B D0,TBB
        BRA INIRTI

RXRDYB:
        MOVE.L #1,D0
        MOVE.B RBB,D1
        BSR ESCCAR
        CMP.L #$FFFFFFFF,D0
        BEQ FINRTI
        BRA INIRTI
NOINTA:
        BCLR #0,D5      *Ponemos el primer bit a cero para 
                        *inhabilitar las interrupciones de transmision de la linea A
        MOVE.B D5,IMR
        BRA INIRTI
NOINTB:
        BCLR #4,D5      *Ponemos el quinto bit a cero para 
                        *inhabilitar las interrupciones de transmision de lalinea B
        MOVE.B D5,IMR
        BRA INIRTI
FINRTI:    
        MOVEM.L (A7)+,D0-D2
        RTE
**************************** FIN RTI ******************************************

**************************** PROGRAMA PRINCIPAL **********************************************
BUFFER: DS.B 2100       * Buffer para lectura y escritura de caracteres
PARDIR: DC.L 0          * Direccion que se pasa como parametro
PARTAM: DC.W 0          * Tamano que se pasa como par´ametro
CONTC:  DC.W 0          * Contador de caracteres a imprimir
DESA:   EQU 0           * Descriptor lınea A
DESB:   EQU 1           * Descriptor lınea B
TAMBS:  EQU 30          * Tamano de bloque para SCAN
TAMBP:  EQU 7           * Tamano de bloque para PRINT

* Manejadores de excepciones

INICIO:
        MOVE.L #BUS_ERROR,8
        MOVE.L #ADDRESS_ER,12
        MOVE.L #ILLEGAL_IN,16
        MOVE.L #PRIV_VIOLT,32
        MOVE.L  #ILLEGAL_IN,40
        MOVE.L  #ILLEGAL_IN,44
        BSR INIT
        MOVE.W #$2000,SR
BUCPR:  MOVE.W #TAMBS,PARTAM
        MOVE.L #BUFFER,PARDIR
OTRAL:  MOVE.W PARTAM,-(A7)
        MOVE.W #DESA,-(A7)
        MOVE.L PARDIR,-(A7)
ESPL:   BSR SCAN
        ADD.L #8,A7
        ADD.L D0,PARDIR
        SUB.W D0,PARTAM
        BNE OTRAL
        MOVE.W #TAMBS,CONTC
        MOVE.L #BUFFER,PARDIR
OTRAE:  MOVE.W #TAMBP,PARTAM
ESPE:   MOVE.W PARTAM,-(A7)
        MOVE.W #DESB,-(A7)
        MOVE.L PARDIR,-(A7)
        BSR PRINT
        ADD.L #8,A7
        ADD.L D0,PARDIR
        SUB.W D0,CONTC
        BEQ SALIR
        SUB.W D0,PARTAM
        BNE ESPE
        CMP.W #TAMBP,CONTC
        BHI OTRAE
        MOVE.W CONTC,PARTAM
        BRA ESPE
SALIR:  BRA BUCPR

BUS_ERROR:      BREAK        * Bus error handler
                NOP
ADDRESS_ER:     BREAK       * Address error handler
                NOP
ILLEGAL_IN:     BREAK       * Illegal instruction handler
                NOP
PRIV_VIOLT:     BREAK       * Privilege violation handler
                NOP
**************************** FIN PROGRAMA PRINCIPAL ******************************************

INCLUDE bib_aux.s

