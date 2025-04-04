*------------------------------
* Inicializa el SP y el PC
*------------------------------
 ORG $0
 DC.L $8000 * Pila
 DC.L INICIO * PC
 ORG $400
*------------------------------
* Definicion de equivalencias
*------------------------------
MR1A EQU $EFFC01 * de modo A (escritura)
MR2A EQU $EFFC01 * de modo A (2º escritura)
SRA EQU $EFFC03 * de estado A (lectura)
CSRA EQU $EFFC03 * de seleccion de reloj A (escritura)
CRA EQU $EFFC05 * de control A (escritura)
RBA EQU $EFFC07 * buffer recepcion A (lectura)
TBA EQU $EFFC07 * buffer transmision A (escritura)
ACR EQU $EFFC09 * de control auxiliar
ISR EQU $EFFC0B * de estado de interrupcion (lectura)
IMR EQU $EFFC0B * de mascara de interrupcion (escritura)
MR1B EQU $EFFC11 * de modo B (escritura)
MR2B EQU $EFFC11 * de modo B (2º escritura)
SRB EQU $EFFC13 * de estado B (lectura)
CSRB EQU $EFFC13 * de seleccion de reloj B (escritura)
CRB EQU $EFFC15 * de control B (escritura)
RBB EQU $EFFC17 * buffer recepcion B (lectura)
TBB EQU $EFFC17 * buffer transmision B (escritura)
IVR EQU $EFFC19 * de vector de interrupcion
*--- Buffers ---*
BSA: DS.B 2001 * Buffer scan A
BSB: DS.B 2001 * Buffer scan B
BPA: DS.B 2001 * Buffer print A
BPB: DS.B 2001 * Buffer print B
*--- Contadores ---*
CSA: DC.W 0 * Contador de caracteres scan A
CSB: DC.W 0 * Contador de caracteres scan B
CPA: DC.W 0 * Contador de caracteres print A
CPB: DC.W 0 * Contador de caracteres print B
*--- Punteros ---*
PISA: DC.L 0 * Puntero inicio caracteres scan A (lectura)
PISB: DC.L 0 * Puntero inicio caracteres scan B (lectura)
PIPA: DC.L 0 * Puntero inicio caracteres print A (lectura)
PIPB: DC.L 0 * Puntero inicio caracteres print B (lectura)
PFSA: DC.L 0 * Puntero final caracteres scan A (escritura)
PFSB: DC.L 0 * Puntero final caracteres scan B (escritura)
PFPA: DC.L 0 * Puntero final caracteres print A (escritura)
PFPB: DC.L 0 * Puntero final caracteres print B (escritura)
*--- Variables Auxiliares ---*
IMRC: DC.B 0 * Copia de IMR
RCA: DC.W 0 * Contador de retorno de carro puerto A
RCB: DC.W 0 * Contador de retorno de carro puerto B
SLA: DC.W 0 * Salto de linea puerto A
SLB: DC.W 0 * Salto de linea puerto B
*----- Programa de Prueba -----*
BUFFER:DS.B 2100 * Buffer para lectura y escritura de caracteres
CONTL: DC.W 0 * Contador de lineas
CONTC:DC.W 0 * Contador de caracteres
DIRLEC:DC.L 0 * Direccion de lectura para SCAN
DIRESC:DC.L 0 * Direccion de escritura para PRINT
TAME: DC.W 0 * Tamano de escritura para print
DESA: EQU 0 * Descriptor linea A
DESB: EQU 1 * Descriptor linea B
NLIN: EQU 10 * Numero de lineas a leer
TAML: EQU 30 * Tamano de linea para SCAN
TAMB: EQU 5 * Tamano de bloque para PRINT
*---------------------------- INIT --------------------------------------------------*
INIT:
 MOVE.B #%00000011,MR1A * 8 bits, 1 interrupcion por caracter(RxRDY).
 MOVE.B #%00000011,MR1B * 8 bits, 1 interrupcion por caracter(RxRDY).
 MOVE.B #%00000000,MR2A * Eco desactivado en A.
 MOVE.B #%00000000,MR2B * Eco desactivado en B.
 MOVE.B #%11001100,CSRA * Velocidad de A = 38400 bps.
 MOVE.B #%11001100,CSRB * Velocidad de B = 38400 bps.
 MOVE.B #%00000000,ACR * Conjunto 1 de velocidades.
 MOVE.B #%00000101,CRA * Transmision y recepcion activados en A.
 MOVE.B #%00000101,CRB * Transmision y recepcion activados en B.
 MOVE.B #%01000000,IVR * Vector de interrupcion 40(hexadecimal).
 MOVE.B #%00100010,IMR * Recepcion habilitada en ambos puertos.
 MOVE.B #%00100010,IMRC * Copia de IMR.
 MOVE.L #RTI,256
*--- Inicializar punteros ---*
 MOVE.L #BSA,PISA * Puntero inicio caracteres scan A
 MOVE.L #BSB,PISB * Puntero inicio caracteres scan B
 MOVE.L #BPA,PIPA * Puntero inicio caracteres print A
 MOVE.L #BPB,PIPB * Puntero inicio caracteres print B
 MOVE.L #BSA,PFSA * Puntero fin caracteres scan A
 MOVE.L #BSB,PFSB * Puntero fin caracteres scan B
 MOVE.L #BPA,PFPA * Puntero fin caracteres print A
 MOVE.L #BPB,PFPB * Puntero fin caracteres print B
 RTS
*---------------------------- FIN INIT ----------------------------------------------*
*---------------------------- LEECAR ------------------------------------------------*
LEECAR:
MOVEM A0/A1,-(A7)
BTST #$00,D0
BEQ LEELNA * Si es un 0 estamos en la linea A
BRA LEELNB * Si es un 1 estamos en la linea B
*--- linea A ---*
LEELNA:
BTST #$01,D0
BEQ LEELAREC * Si es un 0 estamos en el buffer de recepcion
BRA LEELATRNS * Si es un 1 estamos en el buffer de transm
*--- linea B ---*
LEELNB:
BTST #$01,D0
BEQ LEELBREC * Si es un 0 estamos en el buffer de recepcion
BRA LEELBTRNS * Si es un 1 estamos en el buffer de transm
*--- Buffer de transmision linea A ---*
LEELATRNS:
MOVE.L #BPA,A0 * Guardo direccion de inicio del buffer
ADDA.L #$7d0,A0 * Avanzo para conocer el final del buffer
CMP.W #0,CPA * Si el contador = 0, buffer vacio
BEQ VACIO
MOVE.L PIPA,A1 * Guardamos la direccion de donde extraer el
caracter
MOVE.B (A1),D0 * Guardamos el caracter en D0
ADD.L #$01,(PIPA) * Avanzamos el puntero (lectura)
SUB.W #1,CPA * Contador --
CMP.L PIPA,A0 * Comprobamos si es el final del buffer
BNE FINL
MOVE.L #BPA,PIPA * Si es el final, redireccionamos al inicio
BRA FINL
*--- Buffer de recepcion linea A ---*
LEELAREC:
MOVE.L #BSA,A0 * Guardo direccion de inicio del buffer
ADDA.L #$7d0,A0 * Avanzo para conocer el final del buffer
CMP.W #0,CSA * Si el contador = 0, buffer vacio
BEQ VACIO
MOVE.L PISA,A1 * Guardamos la direccion de donde extraer el
caracter
MOVE.B (A1),D0 * Guardamos el caracter en D0
ADD.L #$01,(PISA) * Avanzamos el puntero (lectura)
SUB.W #1,CSA * Contador --
CMP.L PISA,A0 * Comprobamos si es el final del buffer
BNE FINL
MOVE.L #BSA,PISA * Si es el final, redireccionamos al inicio
BRA FINL
*--- Buffer de transmision linea B ---*
LEELBTRNS:
MOVE.L #BPB,A0 * Guardo direccion de inicio del buffer
ADDA.L #$7d0,A0 * Avanzo para conocer el final del buffer
CMP.W #0,CPB * Si el contador = 0, buffer vacio
BEQ VACIO
MOVE.L PIPB,A1 * Guardamos la direccion de donde extraer el
caracter
MOVE.B (A1),D0 * Guardamos el caracter en D0
ADD.L #$01,(PIPB) * Avanzamos el puntero (lectura)
SUB.W #1,CPB * Contador --
CMP.L PIPB,A0 * Comprobamos si es el final del buffer
BNE FINL
MOVE.L #BPB,PIPB * Si es el final, redireccionamos al inicio
BRA FINL
*--- Buffer de recepcion linea B ---*
LEELBREC:
MOVE.L #BSB,A0 * Guardo direccion de inicio del buffer
ADDA.L #$7d0,A0 * Avanzo para conocer el final del buffer
CMP.W #0,CSB * Si el contador = 0, buffer vacio
BEQ VACIO
MOVE.L PISB,A1 * Guardamos la direccion de donde extraer el
caracter
MOVE.B (A1),D0 * Guardamos el caracter en D0
ADD.L #$01,(PISB) * Avanzamos el puntero (lectura)
SUB.W #1,CSB * Contador --
CMP.L PISB,A0 * Comprobamos si es el final del buffer
BNE FINL
MOVE.L #BSB,PISB * Si es el final, redireccionamos al inicio
BRA FINL
*--- Puerto vacio ---*
VACIO:
MOVE.L #$FFFFFFFF,D0 * Devolvemos el valor para buffer vacio
*--- Fin de LEECAR ---*
FINL:
MOVEM (A7)+,A0/A1
RTS
*---------------------------- FIN LEECAR --------------------------------------------*
*---------------------------- ESCCAR ------------------------------------------------*
ESCCAR:
MOVEM A0/A1,-(A7)
BTST #$00,D0
BEQ ESCLNA * Si es un 0 estamos en la linea A
BRA ESCLNB * Si es un 1 estamos en la linea B
*--- linea A ---*
ESCLNA:
BTST #$01,D0
BEQ ESCLAREC * Si es un 0 estamos en el buffer de recepcion
BRA ESCLATRNS * Si es un 1 estamos en el buffer de
transmision
*--- linea B ---*
ESCLNB:
BTST #$01,D0
BEQ ESCLBREC * Si es un 0 estamos en el buffer de recepcion
BRA ESCLBTRNS * Si es un 1 estamos en el buffer de
transmision
*--- Buffer de transmision linea A ---*
ESCLATRNS:
MOVE.L #BPA,A0 * Guardo direccion de inicio del buffer
ADDA.L #$7d0,A0 * Avanzo para conocer el final del buffer
CMP.W #2000,CPA * Si el contador = 2000, buffer lleno
BEQ LLENO
MOVE.L PFPA,A1 * Guardamos la direccion donde
insertaremos D1
MOVE.B D1,(A1) * Guardamos el caracter en el buffer
ADD.L #$01,(PFPA) * Avanzamos a la primera pos del buffer
(escritura)
ADD.W #1,CPA * Contador ++
MOVE.L #0,D0 * Notificamos que ha ido bien
CMP.L PFPA,A0 * Comprobamos si estamos al final
BNE FINE
MOVE.L #BPA,PFPA * Si es la ultima, redireccionamos al inicio
BRA FINE
*--- Buffer de recepcion linea A ---*
ESCLAREC:
MOVE.L #BSA,A0 * Guardo direccion de inicio del buffer
ADDA.L #$7d0,A0 * Avanzo para conocer el final del buffer
CMP.W #2000,CSA * Si el contador = 2000, buffer lleno
BEQ LLENO
MOVE.L PFSA,A1 * Guardamos la direccion donde
insertaremos D1
MOVE.B D1,(A1) * Guardamos el caracter en el buffer
ADD.L #$01,(PFSA) * Avanzamos a la primera pos del buffer
(escritura)
ADD.W #1,CSA * Contador ++
MOVE.L #0,D0 * Notificamos que ha ido bien
CMP.L PFSA,A0 * Comprobamos si estamos al final
BNE FINE
MOVE.L #BSA,PFSA * Si es la ultima, redireccionamos al inicio
BRA FINE
*--- Buffer de transmision linea B ---*
ESCLBTRNS:
MOVE.L #BPB,A0 * Guardo direccion de inicio del buffer
ADDA.L #$7d0,A0 * Avanzo para conocer el final del buffer
CMP.W #2000,CPB * Si el contador = 2000, buffer lleno
BEQ LLENO
MOVE.L PFPB,A1 * Guardamos la direccion donde
insertaremos D1
MOVE.B D1,(A1) * Guardamos el caracter en el buffer
ADD.L #$01,(PFPB) * Avanzamos a la primera pos del buffer
(escritura)
ADD.W #1,CPB * Contador ++
MOVE.L #0,D0 * Notificamos que ha ido bien
CMP.L PFPB,A0 * Comprobamos si estamos al final
BNE FINE
MOVE.L #BPB,PFPB * Si es la ultima, redireccionamos al inicio
BRA FINE
*--- Buffer de recepcion linea B ---*
ESCLBREC:
MOVE.L #BSB,A0 * Guardo direccion de inicio del buffer
ADDA.L #$7d0,A0 * Avanzo para conocer el final del buffer
CMP.W #2000,CSB * Si el contador = 2000, buffer lleno
BEQ LLENO
MOVE.L PFSB,A1 * Guardamos la direccion donde
insertaremos D1
MOVE.B D1,(A1) * Guardamos el caracter en el buffer
ADD.L #$01,(PFSB) * Avanzamos a la primera pos del buffer
(escritura)
ADD.W #1,CSB * Contador ++
MOVE.L #0,D0 * Notificamos que ha ido bien
CMP.L PFSB,A0 * Comprobamos si estamos al final
BNE FINE
MOVE.L #BSB,PFSB * Si es la ultima, redireccionamos al inicio
BRA FINE
*--- Buffer lleno ---*
LLENO:
MOVE.L #$FFFFFFFF,D0
*--- Fin de ESCCAR ---*
FINE:
MOVEM (A7)+,A0/A1
RTS
*---------------------------- FIN ESCCAR --------------------------------------------*
*---------------------------- LINEA -------------------------------------------------*
LINEA:
MOVEM D2/D7/A0,-(A7)
MOVE.L #0,D7 * Contador de caracteres
BTST #$00,D0
BEQ LINLNA * Si es un 0 estamos en la linea A
BRA LINLNB * Si es un 1 estamos en la linea B
*--- linea A ---*
LINLNA:
BTST #$01,D0
BEQ LINLAREC * Si es un 0 estamos en el buffer de recepcion
BRA LINLATRNS * Si es un 1 estamos en el buffer de
transmision
*--- linea B ---*
LINLNB:
BTST #$01,D0
BEQ LINLBREC * Si es un 0 estamos en el buffer de recepcion
BRA LINLBTRNS * Si es un 1 estamos en el buffer de
transmision
*--- Buffer de transmision linea A ---*
LINLATRNS:
CMP.W #0,CPA * Comprobamos si el buffer esta vacio
BEQ LVACIO
MOVE.L PIPA,A0 * Guardamos direccion del puntero de inicio
MOVE.L #BPA,A1 * Guardo direccion de inicio del buffer
ADDA.L #$7d0,A1 * Avanzo para conocer el final del buffer
BPABUC: *--- Bucle BPA ---*
CMP.W CPA,D7 * Comprobamos si hemos recorrido todo el
buffer
BEQ LVACIO
MOVE.B (A0),D2 * Guardamos el caracter
ADD.L #1,D7 * Incrementamos contador
CMP.B #13,D2 * Comprobamos si encontramos un retorno de
carro
BEQ ULIN
ADD.L #1,A0 * Avanzamos posicion
BRA BPABUC
MOVE.L #BPA,A0 * Si es la ultima, redireccionamos al
inicio
BRA BPABUC
*--- Buffer de recepcion linea A ---*
LINLAREC:
CMP.W #0,CSA * Comprobamos si el buffer esta vacio
BEQ LVACIO
MOVE.L PISA,A0 * Guardamos direccion del puntero de inicio
MOVE.L #BSA,A1 * Guardo direccion de inicio del buffer
ADDA.L #$7d0,A1 * Avanzo para conocer el final del buffer
BSABUC: *--- Bucle BSA ---*
CMP.W CSA,D7 * Comprobamos si hemos recorrido todo el
buffer
BEQ LVACIO
MOVE.B (A0),D2 * Guardamos el caracter
ADD.L #1,D7 * Incrementamos contador
CMP.B #13,D2 * Comprobamos si encontramos un retorno de
carro
BEQ ULIN
ADD.L #1,A0 * Avanzamos posicion
CMP.L A1,A0 * Comprobamos si estamos al final
BNE BSABUC
MOVE.L #BSA,A0 * Si es la ultima, redireccionamos al
inicio
BRA BSABUC
*--- Buffer de transmision linea B ---*
LINLBTRNS:
CMP.W #0,CPB * Comprobamos si el buffer esta vacio
BEQ LVACIO
MOVE.L PIPB,A0 * Guardamos direccion del puntero de inicio
MOVE.L #BPB,A1 * Guardo direccion de inicio del buffer
ADDA.L #$7d0,A1 * Avanzo para conocer el final del buffer
BPBBUC: *--- Bucle BPB ---*
CMP.W CPB,D7 * Comprobamos si hemos recorrido todo el
buffer
BEQ LVACIO
MOVE.B (A0),D2 * Guardamos el caracter
ADD.L #1,D7 * Incrementamos contador
CMP.B #13,D2 * Comprobamos si encontramos un retorno de
carro
BEQ ULIN
ADD.L #1,A0 * Avanzamos posicion
CMP.L A1,A0 * Comprobamos si estamos al final
BNE BPBBUC
MOVE.L #BPB,A0 * Si es la ultima, redireccionamos al
inicio
BRA BPBBUC
*--- Buffer de recepcion linea B ---*
LINLBREC:
CMP.W #0,CSB * Comprobamos si el buffer esta vacio
BEQ LVACIO
MOVE.L PISB,A0 * Guardamos direccion del puntero de inicio
MOVE.L #BSB,A1 * Guardo direccion de inicio del buffer
ADDA.L #$7d0,A1 * Avanzo para conocer el final del buffer
BSBBUC: *--- Bucle BSB ---*
CMP.W CSB,D7 * Comprobamos si hemos recorrido todo el
buffer
BEQ LVACIO
MOVE.B (A0),D2 * Guardamos el caracter
ADD.L #1,D7 * Incrementamos contador
CMP.B #13,D2 * Comprobamos si encontramos un retorno de
carro
BEQ ULIN
ADD.L #1,A0 * Avanzamos posicion
CMP.L A1,A0 * Comprobamos si estamos al final
BNE BSBBUC
MOVE.L #BSB,A0 * Si es la ultima, redireccionamos al
inicio
BRA BSBBUC
*--- Buffer vacio o sin una linea completa ---*
LVACIO:
MOVE.L #0,D0 * Notificamos que el buffer esta vacio o no hay
linea
BRA FINLIN
*--- Transmision del numero de caracteres ---*
ULIN:
MOVE.L D7,D0 * Metemos en D0 en numero de caracteres
*--- Fin de linea ---*
FINLIN:
MOVEM (A7)+,D2/D7/A0
RTS
*---------------------------- FIN LINEA ---------------------------------------------*
*---------------------------- SCAN --------------------------------------------------*
SCAN:
LINK A6,#-12 * Se crea el marco de pila
MOVE.W 14(A6),D2 * Se guarda en D2 el Tamano
MOVE.W 12(A6),D3 * Se guarda en D3 el Descriptor
MOVE.L 8(A6),A0 * Se guarda en A0 el Buffer
*--- Identificar Puerto ---*
CMP.W #0,D3 * Comprobamos si vamos al puerto A
BEQ LASCAN
CMP.W #1,D3 * Comprobamos si vamos al puerto B
BEQ LBSCAN
BRA SERROR * Cualquier otro valor, error
*--- Puerto A Scan ---*
LASCAN:
BCLR #$01,D3 * Preparamos para acceder al buffer de
recepcion(00)
MOVE.L D3,D0 * Guardamos en D0 el buffer a usar
BSR LINEA
CMP.L #0,D0 * Comprobamos si hay linea disponible
BEQ NSCAN * Si no hay salimos
CMP.L D2,D0 * Comparamos Tamano y la longitud de linea
BGT NSCAN * Si la linea es mayor salimos
MOVE.L D0,D7 * n
MOVE.L #0,D6 * Contador
BLASCAN: *--- Buffer linea A ---*
MOVE.L D3,D0 * Guardamos en D0 el buffer a usar
BSR LEECAR
MOVE.B D0,(A0) * Guardamos caracter en el buffer
ADD.L #1,A0 * Avanzamos posicion del buffer
ADD.L #1,D6 * Incrementamos contador
CMP.L D6,D7 * Comprobamos si hemos pasado toda la linea
BNE BLASCAN
MOVE.L D7,D0 * Guardamos n en D0
BRA FNSCAN
*--- Puerto B Scan ---*
LBSCAN:
BCLR #$01,D3 * Preparamos para acceder al buffer de
recepcion(01)
MOVE.L D3,D0 * Guardamos en D0 el buffer a usar
BSR LINEA
CMP.L #0,D0 * Comprobamos si hay linea disponible
BEQ NSCAN * Si no hay salimos
CMP.L D2,D0 * Comparamos Tamano y la longitud de linea
BGT NSCAN * Si la linea es mayor salimos
MOVE.L D0,D7 * n
MOVE.L #0,D6 * Contador
BLBSCAN: *--- Buffer linea B ---*
MOVE.L D3,D0 * Guardamos en D0 el buffer a usar
BSR LEECAR
MOVE.B D0,(A0) * Guardamos caracter en el buffer
ADD.L #1,A0 * Avanzamos posicion del buffer
ADD.L #1,D6 * Incrementamos contador
CMP.L D6,D7 * Comprobamos si hemos pasado toda la linea
BNE BLBSCAN
MOVE.L D7,D0 * Guardamos n en D0
BRA FNSCAN
*--- Descriptor no valido ---*
SERROR:
MOVE.L #-1,D0 * Se carga el D0 el valor de error
BRA FNSCAN
*--- No hay linea o la linea es mayor que el tamano ---*
NSCAN:
MOVE.L #0,D0
*--- Fin de SCAN ---*
FNSCAN:
UNLK A6 * Se destruye el marco de pila
RTS
*---------------------------- FIN SCAN ----------------------------------------------*
*---------------------------- PRINT -------------------------------------------------*
PRINT:
LINK A6,#-12 * Se crea el marco de pila
MOVE.W 14(A6),D2 * Se guarda en D2 el Tamano
MOVE.W 12(A6),D3 * Se guarda en D3 el Descriptor
MOVE.L 8(A6),A0 * Se guarda en A0 el Buffer
*--- Identificar Puerto ---*
CMP.W #0,D3 * Comprobamos si vamos al puerto A
BEQ LAPRINT
CMP.W #1,D3 * Comprobamos si vamos al puerto B
BEQ LBPRINT
BRA PERROR * Cualquier otro valor, error
*--- Puerto A Print ---*
LAPRINT:
MOVE.L #0,D7 * Contador
CMP.W #0,D2 * Comprobamos que hay caracteres que
escribir
BEQ LLPRINT
BSET #$01,D3 * Preparamos para acceder al buffer de
transmision(11)
BLAPRINT: *--- Buffer linea A ---*
MOVE.L D3,D0 * Guardamos en D0 el buffer a usar
MOVE.B (A0),D1 * Sacamos un caracter de buffer
BSR ESCCAR
CMP.L #0,D0 * Comprobamos que se ha insertado bien
BNE LLPRINT
CMP.L #13,D1 * Comprobamos si hemos escrito un retorno
de carro
BNE NORCA
ADD.W #1,RCA * Aumentamos contador de retorno de carro
puerto A
NORCA: *--- No hay RC ---*
ADD.L #1,D7 * Contador++
ADD.L #1,A0 * Avanzamos en el buffer
CMP.L D7,D2 * Comparamos Tamano con contador
BNE BLAPRINT
CMP.W #0,RCA * Comprobamos si hay una linea
BEQ LLPRINT
BSET #$00,IMRC * Activamos transmision en puerto A
MOVE.B IMRC,IMR
BRA LLPRINT
*--- Puerto B Print ---*
LBPRINT:
MOVE.L #0,D7 * Contador
CMP.W #0,D2 * Comprobamos que hay caracteres que
escribir
BEQ LLPRINT
BSET #$01,D3 * Preparamos para acceder al buffer de
transmision(11)
BLBPRINT: *--- Buffer linea B ---*
MOVE.L D3,D0 * Guardamos en D0 el buffer a usar
MOVE.B (A0),D1 * Sacamos un caracter de buffer
BSR ESCCAR
CMP.L #0,D0 * Comprobamos que se ha insertado bien
BNE LLPRINT
CMP.L #13,D1 * Comprobamos si hemos escrito un retorno
de carro
BNE NORCB
ADD.W #1,RCB * Aumentamos contador de retorno de carro
puerto B
NORCB: *--- No hay RC ---*
ADD.L #1,D7 * Contador++
ADD.L #1,A0 * Avanzamos en el buffer
CMP.L D7,D2 * Comparamos Tamano+1 con contador
BNE BLBPRINT
CMP.W #1,RCB * Comprobamos si hay una linea
BEQ LLPRINT
BSET #$04,IMRC * Activamos transmision en puerto B
MOVE.B IMRC,IMR
BNE LLPRINT
*--- Caracteres encolados ---*
LLPRINT:
MOVE.L D7,D0 * Guardamos en D0 el numero de caracteres
escritos
BRA FNPRINT
*--- Descriptor no valido ---*
PERROR:
MOVE.L #-1,D0 * Se carga el D0 el valor de error
BRA FNSCAN
*--- Fin de PRINT ---*
FNPRINT:
UNLK A6 * Se destruye el marco de pila
RTS
*---------------------------- FIN PRINT ---------------------------------------------*
*---------------------------- RTI -------------------------------------------------*
RTI:
MOVEM D0/D1,-(A7)
*--- Identificacion de la interrupcion ---*
MOVE.B ISR,D1 * Cargamos ISR en D1
AND.B IMRC,D1 * Hacemos un AND de IMR e ISR
BTST #$00,D1
BNE TARTI * Transmision Puerto A
BTST #$01,D1
BNE RARTI * Recepcion Puerto A
BTST #$04,D1
BNE TBRTI * Transmision Puerto B
BTST #$05,D1
BNE RBRTI * Recepcion Puerto B
BRA FINRTI
*--- Tratamiento de la interrupcion ---*
*--- Interrupcion de recepcion ---*
RARTI: *--- Recepcion Puerto A ---*
MOVE.B RBA,D1 * Guardamos el caracter en D1
MOVE.L #0,D0 * Elegimos el buffer de recepcion A(00)
BSR ESCCAR
BRA FINRTI
RBRTI: *--- Recepcion Puerto B ---*
MOVE.B RBB,D1 * Guardamos el caracter en D1
MOVE.L #1,D0 * Elegimos el buffer de recepcion B(01)
BSR ESCCAR
BRA FINRTI
*--- Interrupcion de transmision ---*
TARTI: *--- Transmision Puerto A ---*
CMP.W #1,SLA * Compruebo si he transmitido un retorno de
carro
BEQ SLARTI
CMP.W #0,RCA * Comprobamos que hay líneas por transmitir
puerto A
BNE LTARTI
BCLR #$00,IMRC * Inhibimos interrupcion de transmision en A
MOVE.B IMRC,IMR
BRA FINRTI
LTARTI:
MOVE.L #2,D0 * Elegimos el buffer de transmision A(10)
BSR LEECAR
CMP.L #13,D0 * Comprobamos si es un retorno de carro
BNE TTARTI
*--- Transmision de Retorno de Carro ---*
MOVE.W #1,SLA * Hemos transmitido un retorno de carro
BRA TTARTI
SLARTI:
MOVE.L #10,D0 * Tranmitimos
MOVE.W #0,SLA * Salto de línea transmitido
SUB.W #1,RCA * Reducimos contador de líneas puerto A
TTARTI:
MOVE.B D0,TBA * Transmitimos el caracter
BRA FINRTI
TBRTI: *--- Transmision Puerto B ---*
CMP.W #1,SLB * Compruebo si he transmitido un retorno de
carro
BEQ SLBRTI
CMP.W #0,RCB * Comprobamos que hay líneas por transmitir
puerto B
BNE LTBRTI
BCLR #$04,IMRC * Inhibimos interrupcion de transmision en B
MOVE.B IMRC,IMR
BRA FINRTI
LTBRTI:
MOVE.L #3,D0 * Elegimos el buffer de transmision B(11)
BSR LEECAR
CMP.L #13,D0
BNE TTBRTI
*--- Transmision de Retorno de Carro ---*
MOVE.W #1,SLB * Hemos transmitido un retorno de carro
BRA TTBRTI
SLBRTI:
MOVE.L #10,D0 * Transmitimos
MOVE.W #0,SLB * Salto de línea transmitido
SUB.W #1,RCB * Reducimos contador de líneas puerto B
TTBRTI:
MOVE.B D0,TBB * Transmitimos el caracter
FINRTI:
MOVEM (A7)+,D0/D1
RTE
*---------------------------- FIN RTI ---------------------------------------------*
*---------------------------- PROGRAMA PRINCIPAL ------------------------------------*
INICIO: * Manejadores de excepciones
MOVE.L #BUS_ERROR,8 * Bus error handler
MOVE.L #ADDRESS_ER,12 * Address error handler
MOVE.L #ILLEGAL_IN,16 * Illegal instruction handler
MOVE.L #PRIV_VIOLT,32 * Privilege violation handler
BSR INIT
MOVE.W #$2000,SR * Permite interrupciones
BUCPR: MOVE.W #0,CONTC * Inicializa contador de caracteres
MOVE.W #NLIN,CONTL * Inicializa contador de lineas
MOVE.L #BUFFER,DIRLEC * Direccion de lectura = comienzo del
buffer
OTRAL: MOVE.W #TAML,-(A7) * Tamano maximo de la linea
MOVE.W #DESA,-(A7) * Puerto A
MOVE.L DIRLEC,-(A7) * Direccion de lectura
ESPL: BSR SCAN
CMP.L #0,D0
BEQ ESPL * Si no se ha leido una linea se intenta de nuevo
ADD.L #8,A7 * Restablece la pila
ADD.L D0,DIRLEC * Calcula la nueva direccion de lectura
ADD.W D0,CONTC * Actualiza el numero de caracteres leidos
SUB.W #1,CONTL * Actualiza el numero de lineas leidas. Si no
BNE OTRAL * se han leido todas las lineas se vuelve a leer
MOVE.L #BUFFER,DIRLEC * Direccion de lectura = comienzo del
buffer
OTRAE: MOVE.W #TAMB,TAME * Tamano de escritura = Tamano de bloque
ESPE: MOVE.W TAME,-(A7) * Tamano de escritura
MOVE.W #DESB,-(A7) * Puerto B
MOVE.L DIRLEC,-(A7) * Direccion de lectura
BSR PRINT
ADD.L #8,A7 * Restablece la pila
ADD.L D0,DIRLEC * Calcula la nueva direccion del buffer
SUB.W D0,CONTC * Actualiza el contador de caracteres
BEQ SALIR * Si no quedan caracteres se acaba
SUB.W D0,TAME * Actualiza el tamano de escritura
BNE ESPE * Si no se ha escrito todo el bloque se insiste
CMP.W #TAMB,CONTC * Si el no de caracteres que quedan es menor que el
* tamano establecido se transmite ese numero
BHI OTRAE * Siguiente bloque
MOVE.W CONTC,TAME
BRA ESPE * Siguiente bloque
SALIR: BRA BUCPR
FIN: BREAK
BUS_ERROR:
BREAK * Bus error handler
NOP
ADDRESS_ER:
BREAK * Address error handler
NOP
ILLEGAL_IN:
BREAK * Illegal instruction handler
NOP
PRIV_VIOLT:
BREAK * Privilege violation handler
NOP
*---------------------------- FIN PROGRAMA PRINCIPAL --------------------------------*







