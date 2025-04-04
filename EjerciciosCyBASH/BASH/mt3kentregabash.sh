# #! /bin/bash -x


# Queremos crear una funcion para poder hacer echo de errores facilmente:
errcho() {
    echo "$@" 1>&2;
}

# Este es el handle para hacer 'parsing' de los argumentos.

# Si el numero de argumentos no es el correcto
if [ $# -ne 1 ]; then
    errcho "minientrega.sh: Error(EX_USAGE), uso incorrecto del mandato. "Success"">&2 #supongo que para depurar
    errcho "minientrega.sh: <<descripción detallada del error>>"
    exit 65
fi

# Imprimimos la ayuda
if [[ $1 == "-h" || $1 == "--help" ]]; then
    echo "minientrega.sh: Uso: minientrega.sh ID_PRACTICA"
    echo "minientrega.sh: Este programa realiza la entrega de una practica 'ID_PRACTICA' siempre que sea posible"
    exit 0
fi 

# Debemos comprobar que la variable MINIENTREGA_CONF esta declarada y que el 
# directorio que representa es legible

# Comprobamos que MINIENTREGA_CONF esta 

if [[ ! -d $MINIENTREGA_CONF ]]; then
    errcho "minientrega.sh: Error, no se pudo realizar la entrega"
    errcho "minientrega.sh+ no es accesible el directorio \""$MINIENTREGA_CONF"\""
    exit 64
fi

# Ahora debemos comprobar que el archivo es un archivo valido
ID_PRACTICA="$MINIENTREGA_CONF/$1"

if [[ ! -f $ID_PRACTICA ]]; then
    errcho "minientrega.sh: Error, no se pudo realizar la entrega"
    errcho "minientrega.sh+ no es accesible el fichero \"$1\""
    exit 64
fi

# Ahora cargamos las variables del archivo
source $ID_PRACTICA

# Comprobamos si la fecha es legit
# TODO: fix fecha_maxima = 2100
if [[ ! $MINIENTREGA_FECHALIMITE == 20[0-9][0-9]-[0-1][0-9]-[0-3][0-9] ]] || ! date -d "$MINIENTREGA_FECHALIMITE" >/dev/null 2>&1; then
#Esta parte del condicional verifica si la variable $MINIENTREGA_FECHALIMITE no coincide con un patrón específico de fecha. 
#El patrón de fecha parece ser del tipo "AAAA-MM-DD"
# 2 parte comprueba con date(de unix) puede analizar la fecha dada, envia mensaje de exito y de error a /dev/null
    errcho "minientrega.sh: Error, no se pudo realizar la entrega"
    errcho "minientrega.sh+ fecha incorrecta "$MINIENTREGA_FECHALIMITE""
    exit 65
fi

# Tomamos la 2 fechas relevantes para la comparacion.
# Como en la variable del archivo, MINIENTREGA_FECHALIMITE
# es un String, tenemos que convertirla a una fecha valida.
# Llegados a este punto por el anterior if sabemos que es 
# una fecha valida, asi que la convertimos

FECHA_ACTUAL=$(date +"%F")
FECHA_LIMITE=$(date -d "$MINIENTREGA_FECHALIMITE" +"%F")

# Ahora obtenemos los valores en Segundos para comparar
FECHA_ACTUAL_S=$(date +%s)
FECHA_LIMITE_S=$(date -d "$MINIENTREGA_FECHALIMITE" +%s)

# Comprobamos que la fecha de entrega no se haya pasado
if [[ $FECHA_ACTUAL_S -ge $FECHA_LIMITE_S ]]; then
    errcho "minientrega.sh: Error, no se pudo realizar la entrega"
    errcho "minientrega.sh+ el plazo acabada el "$FECHA_LIMITE""
    exit 65
fi

# Ahora nos encargamos de los archivos de la practica

# Comprobacion de los archivos

for FILE in $MINIENTREGA_FICHEROS; do
    # el -r verifica si es legible
    if [ ! -r $FILE ]; then
        errcho "minientrega.sh: Error, no se pudo realizar la entrega"
        errcho "minientrega.sh+ no es accesible el fichero "$FILE""
        exit 66
    fi
done

DESTINACION="$MINIENTREGA_DESTINO/${USER}"
# asignas a destinacion de minientrega_destino definida en algun lado y ${USER} almacena el nombre del usuario que ejecuta el script
#la barra se usa para por ejemplo crear /home/destino/marcos

# Realizacion de entrega

if [ ! -d $MINIENTREGA_DESTINO ] || ! mkdir -p "$DESTINACION" >/dev/null 2>&1 ; then
    # -d comprueba si es un directorio || si no || crea directorio y redirige la salida estándar (STDOUT) y la salida de error (STDERR) 
    #                                            del comando mkdir a /dev/null, lo que significa que no se mostrarán mensajes de salida ni errores en la pantalla.
    errcho "minientrega.sh: Error, no se pudo realizar la entrega"
    errcho "minientrega.sh+ no se pudo crear el subdirectorio de entrega en "$MINIENTREGA_DESTINO""
    exit 73
fi

# Copiamos los archivos al directorio correcto
for FILE in $MINIENTREGA_FICHEROS; do
    cp $FILE "$DESTINACION"
done

# Si hemos llegado hasta aqui, terminamos con 0 (Terminacion Correcta)
exit 0




###########################################################################################################
#################OTRA MANERA##################################################




# /bin/bash
#comprobar el numero de argumentos
if [ $# -ne "1" ]; then
echo "$0: Error (EX_USAGE), uso incorrecto del mandato. \"Succes\"" >&2
echo "$0+ El numero de argumentos recibidos no es el esperado" >&2
exit 64
fi
# comprobar si pide ayuda
if [ $1 == '-h' -o $1 == '--help' ]; then
echo "$0:-h/--help para la ayuda"
echo "$0+ Copia archivos los ficheros de un directorio de usuario a otro"
exit 0
fi
# Comprobar que existe MINIENTREGA_CONF
if test -z ${MINIENTREGA_CONF}; then
echo "$0 : Error, no se pudo realizar la entrega">&2
echo "$0 + No es accesible el directorio ">&2
exit 64;
fi
# comprobar la variable MINIENTREGA_CONF es un directorio
if test ! -d ${MINIENTREGA_CONF}; then
echo "$0 : Error, no se pudo realizar la entrega">&2
echo "$0+ No es un directorio directorio ">&2
exit 64;
fi
#Comprobar que la variable MINIENTREGA_CONF tiene permiso de lectura
f test ! -r ${MINIENTREGA_CONF}; then
echo "$0 : Error, no se pudo realizar la entrega" >&2
echo "$0+ no se puede leer el directorio ">&2
exit 64;
fi
#Comprobar que se puede escribir en dicho directorio
if test ! -w ${MINIENTREGA_CONF}; then
echo "$0 : Error, no se pudo realizar la entrega">&2
echo "$0+ no se puede escribir en el directorio ">&2
exit 64;
fi
#Comprueba que se puede leer el argumento
if test ! -r ${MINIENTREGA_CONF}/$1; then
echo "$0 : Error, no se pudo realizar la entrega">&2
echo "$0+ no se puede acceder al fichero $1 ">&2
exit 66;
fi
source ${MINIENTREGA_CONF}/$1
#Comprobacion de que la fecha este en el formato correcto
if [ ${#MINIENTREGA_FECHALIMITE} -ne 10 ]; then
echo "$0: Error, no se puedo realizar la entrega">&2
echo "$0: Fecha incorrecta ${MINIENREGA_FECHALIMITE}">&2
exit 65;
fi
if ! [[ ${MINIENTREGA_FECHALIMITE:4:1} == '-' && ${MINIENTREGA_FECHALIMITE:7:1} == '-
' ]]; then
echo "$0: Error, no se puedo realizar la entrega">&2
echo "$0: Fecha incorrecta ${MINIENTREGA_FECHALIMITE}">&2
exit 65;
fi
if [ ${MINIENTREGA_FECHALIMITE:0:4} -gt 2100 ]; then
echo " $0: Error, no se puedo realizar la entrega">&2
echo "$0: Entrega fuera de plazo">&2
exit 65;
fi
if [ ${MINIENTREGA_FECHALIMITE:0:4} -gt 2100 -a ${MINIENTREGA_FECHALIMITE:5:2} -ne
01 ]; then
echo " $0: Error, no se puedo realizar la entrega">&2
echo "$0: Entrega fuera de plazo">&2
exit 65;
fi
if [ ${MINIENTREGA_FECHALIMITE:0:4} -gt 2100 -a ${MINIENTREGA_FECHALIMITE:7:2} -ne
01 ]; then
echo " $0: Error, no se puedo realizar la entrega">&2
echo "$0: Entrega fuera de plazo">&2
exit 65;
fi
#Comprobamos los archivos
for fichero in ${MINIENTREGA_FICHEROS[0]}; do
if !(test -r $fichero) || !(test -e $fichero); then
echo " $0: Error, no se puedo realizar la entrega">&2
echo "$0: No es accesible el fichero $fichero">&2
exit 66;
fi
done
#Existe el directorio de destino
if test ! -d ${MINIENTREGA_DESTINO} || test ! -w ${MINIENTREGA_DESTINO};then
echo " $0: Error, no se puedo realizar la entrega">&2
echo "$0: No se pudo crear el subdirectorio de entrega en ${MINIENTREGA_DESTINO} ">&2
exit 73;
fi
#copiar todos los archivos de MINIENTREGA_FICHEROS
cp ${MINIENTREGA_FICHEROS}/* ${MINIENTREGA_DESTINO}/${USER}
exit 0


