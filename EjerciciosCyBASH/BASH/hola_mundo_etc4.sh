
#!/usr/bin/sh

# Nota: en este ejemplo se ha elegido /usr/bin/sh en vez de /usr/bin/bash
# Nota: sh es un enlace que apunta a dash (en vez de a bash)


# Se va a experimentar con variables,
# argumentos, fichero de configuración, etc.
#
#
# Ejemplos de llamada:
#
#
# (1) Caso de definir MIDIR en variable (de entorno):
# export MIDIR=Dir_Prueba ; ./hola_mundo_etc4.sh
#
#
# (2) Caso de argumento de entrada con fichero de configuración
# donde estará definida la variable MIDIR; se hará source .
# Nota: se hace un unset previo por si estuviera ya definida
# variable MIDIR en llamada previa .
# unset MIDIR ; ./hola_mundo_etc4.sh fich_configuracion.sh
#
# Nota: en el caso de fichero de configuración, éste puede
# definir una variable como FECHA que se va a emplear
# como nombre de directorio destino en una copia.



#EN ESTE CASO LAS VARIABLES NO SE ADQUIEREN CON UN EXPORT, 
#LAS VARIABLES SE COGEN DEL .SH PASADO COMO ARGUMENTO Y SE HACE source


echo "El contenido inicial de la variable MIDIR es: $MIDIR".

# Si la variable MIDIR está vacía, se va a mirar
# si se ha pasado un argumento al script. Si existe
# dicho argumento, se va a considerar como un fichero
# de configuración que definirá la variable MIDIR .

if test -z "MIDIR"; then
  if test $# -eq 1; then
    FICH_CONF=$1
    if test -f "$FICH_CONF" && [ -w "$FICH_CONF" ]; then


# En el caso de que MIDIR fuese vacío, se va a intentar
# obtener un valor en el siguiente bloque:
if test -z "$MIDIR"; then
  # Se va a mirar si existe un argumento de entrada
  echo "El valor de \$# es: $#"
  if [ $# -eq 1 ]; then
    # Existe un argumento, el cual se tomará como un
    # fichero de configuración con información
    FICH_CONF=$1
    echo "FICH_CONF es $FICH_CONF"
    if test -f "$FICH_CONF" && [ -w "$FICH_CONF" ]; then
      # FICH_CONF está definido y existe y es legible
      # Se va a hacer 'source' de dicho fichero:
      source "$FICH_CONF"
      # Ahora MIDIR debería estar definido
    else
      # Mensaje de error en salida error, y se acaba con exit 1
      echo "ERROR: $FICH_CONF no existe o no es legible" >&2
      exit 1
    fi
  else
    # Mensaje de error en salida error, y se acaba con exit 2
    echo 'ERROR: no se ha podido obtener un valor para MIDIR . Adiós...' >&2
    exit 2
  fi
fi


# Ahora MIDIR debería tener un valor
echo "MIDIR es: $MIDIR"

[ -d "$MIDIR" ] || mkdir "$MIDIR" &> /dev/null  #Comprueba si existe el directorio y si no lo creas
cd "$MIDIR" || exit 3 # Si no se puede hacer cd, exit 3

# Se hace touch de algunos ficheros en MIDIR
touch fich1.txt fich2.txt fich3.txt
# Se van a lanzar algunos chmod
chmod 400 fich1.txt fich2.txt fich3.txt
chmod +w fich3.txt

for f in *
do
  test -f $f && echo "$f es un fichero."
  test -r $f && echo "$f es legible."
  test -w $f && echo "$f es modificable."
done


# En el caso de que FECHA exista (caso de fichero
# de configuración), se comprueba si existe
# directorio con ese nombre o bien se intenta crear.
# Y después se copia fich3.txt a dicho directorio
if [ -n "$FECHA" ]; then
  if [ -d "$FECHA" ] || mkdir "$FECHA" &> /dev/null; then
    # Se ha creado con éxito el directorio
    echo "Caso con FECHA ($FECHA). Se lanza cp..."
    cp fich3.txt "$FECHA"
  else
    # Si hay error en la creación del directorio,
    # mensaje de error en salida error y se acaba
    # con exit 4
    echo "ERROR en relación al directorio $FECHA" >&2
    exit 4
  fi
fi

# Se acaba con exit (equivalente a exit 0)
exit

