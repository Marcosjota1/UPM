
#!/usr/bin/sh

# Nota: en este ejemplo se ha elegido /usr/bin/sh en vez de /usr/bin/bash
# Nota: sh es un enlace que apunta a dash (en vez de a bash)

echo "Hola, mundo. Soy $USER y estoy en el equipo $HOSTNAME."
echo "Hola, mundo. Soy \"$USER\", y estoy en el equipo \"$HOSTNAME\"."

echo "Otra forma de mostrar quién soy: $(whoami)."
echo "Otra forma de mostrar quién soy: \"$(whoami)\"."

echo "El contenido de la variable MIDIR es: $MIDIR".

echo "El contenido de la variable MIVAR es: $MIVAR".

echo 'Se va a hacer echo $$'
echo $$










