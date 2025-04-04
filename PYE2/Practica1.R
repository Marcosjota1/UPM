library(boot)
bootR <- 1000;set.seed(2023) #boot.ci(...,conf = 0.95,type=c("norm"))

install.packages("e1071")
install.packages("MASS")
install.packages("fitdistrplus")
library(e1071);library(MASS);library(fitdistrplus)

#Librerias para preguntas de Max Verosimilitud
install.packages("DescTools")
install.packages("PropCIs")
install.packages("rcompanion")
install.packages("EstimationTools")

library(DescTools)
library(PropCIs)
library(rcompanion)
library(EstimationTools)
install.packages("BSDA")
library(BSDA)

#librerias para intervalos de confianza
library(DescTools)
library(PropCIs)
install.packages("stats")
library(stats)

# Definir el conjunto de números
Sample1 <- c(4.681594, 3.718336, 4.986687, 4.505408, 2.638489, 4.729319, 3.476107, 3.300813, 6.069838, 4.026206, 3.465977, 5.32365, 3.67339, 5.40275, 5.021305, 4.57446, 3.285557, 5.394977, 7.125954, 5.634093)

# 1) 
min_value <- min(sample1)
print(min_value)

#2) Primer cuartil
Q1 <- quantile(sample1, probs = 0.25)

#3) Mediana
Q2 <- median(sample1)

#4)Calcular la media
media <- mean(sample1)

#5) Tercer cuartil
Q3 <- quantile(sample1, probs = 0.75)

#6) Maximo Sample1
max <- max(sample1)

install.packages("e1071")
library(e1071)
library(MASS)

#7) Coeficiente de asimetría
skew <- skewness(sample1)

#8) Coeficiente de apuntamiento 
kurt <- kurtosis(sample1)

#9) Media del ajuste a la distribucion normal
fit <- fitdistr(Sample1, "normal")
fit$estimate["mean"]

#10)  
fit <- fitdistr(Sample1, "normal")
fit$estimate["sd"]

#11)  
# Realizar el test de Kolmogorov-Smirnov
resultado_ks <- ks.test(Sample1, "pnorm", mean = fit$estimate["mean"], sd = fit$estimate["sd"])

# Obtener el p-valor
resultado_ks$p.value

#12)
# Estimador de la tasa lambda
1 / fit$estimate["mean"]

#13)  
# Realizar el test de Kolmogorov-Smirnov para ajuste a la distribución exponencial
ks_test_result <- ks.test(Sample1, "pexp")
# Obtener el valor p del test
p_value <- ks_test_result$p.value
print(p_value)

#############################################
Medias_Muestrales_30 <- c(43262.1, 124215.6, 23402.1, 11485.35, 25474.3, 41080.55, 12451.55, 8775.85, 54830.35, 9256, 230180, 26430.8, 42673.05, 23171, 107915.7, 52914.55, 104691.4, 53044.2, 29082.9, 92693.4, 16293.3, 13183.15, 38206.45, 19703.35, 11342.4, 6817.95, 14400.75, 20135.1, 18567.95, 94656.7);
Proporciones_Muestrales_30 <- c(0.2, 0.05, 0.15, 0.05, 0.05, 0, 0.05, 0.1, 0.1, 0, 0.05, 0.2, 0.2, 0.15, 0.05, 0.1, 0.2, 0.05, 0.05, 0.15, 0.1, 0.1, 0.1, 0.1, 0.05, 0.25, 0, 0.05, 0.05, 0.1);
library(fitdistrplus)
#14 / 15)
Medias_Muestrales_30_normalfit <- fitdistr(Medias_Muestrales_30, "normal") #Ajustamos Medias_Muestrales_30 a una distribución normal
Medias_Muestrales_30_normalfit[["estimate"]]

#16)
# Ajuste a distribución normal
fit_medias <- fitdistr(Medias_Muestrales_30, "normal")
# Realizar el test de Kolmogorov-Smirnov
resultado_ks_medias <- ks.test(Medias_Muestrales_30, "pnorm", mean = fit_medias$estimate["mean"], sd = fit_medias$estimate["sd"])
resultado_ks_medias$p.value

#17)
media_proporciones <- mean(Proporciones_Muestrales_30)
print(media_proporciones)

#18)
sd(Proporciones_Muestrales_30)


#19) 
fit_proporciones <- fitdistr(Proporciones_Muestrales_30, "normal")
# Realizar el test de Kolmogorov-Smirnov
resultado_ks_proporciones <- ks.test(Proporciones_Muestrales_30, "pnorm", mean = fit_proporciones$estimate["mean"], sd = fit_proporciones$estimate["sd"])
# Obtener el p-valor
resultado_ks_proporciones$p.value


Sample1 <- c (4.681594, 3.718336, 4.986687, 4.505408, 2.638489, 4.729319, 3.476107, 3.300813, 6.069838, 4.026206, 3.465977, 5.32365, 3.67339, 5.40275, 5.021305, 4.57446, 3.285557, 5.394977, 7.125954, 5.634093);
#20/21)
#Estimación puntual Máxima Verosimilitud
fit_Sample1_maxveros <- maxlogL(x = Sample1, dist = 'dnorm', link= list(over = 'sd', fun="log_link"))
summary(fit_Sample1_maxveros)

#Creamos un dataframe pareando Sample 1 y Sample 2
df <- data.frame(Sample1 = Sample1, Sample2 = Sample2)
#Creamos un subset con las muestras correspondientes a Municipal
df_Municipal <- df[df$Sample2=="Municipal",]
#Creamos un subset con las muestras correspondientes a No-Municipal
df_No_Municipal <- df[df$Sample2!="Municipal",]


#Media de Sample1 condicionado a Utitltiy.Type==Municipal por 
#Máxima Verosimilitud supuesta una distribución normal
fit_df_Municipal_maxveros <- maxlogL(x = df_Municipal$Sample1, dist = 'dnorm', link= list(over = 'sd', fun="log_link"))
#22/23)
summary(fit_df_Municipal_maxveros)

#24/25)
fit_df_No_Municipal_maxveros <- maxlogL(x = df_No_Municipal$Sample1, dist = 'dnorm', link= list(over = 'sd', fun="log_link"))
summary(fit_df_No_Municipal_maxveros)

#26)
Sample1 <- c(4.681594, 3.718336, 4.986687, 4.505408, 2.638489, 4.729319, 3.476107, 3.300813, 6.069838, 4.026206, 3.465977, 5.32365, 3.67339, 5.40275, 5.021305, 4.57446, 3.285557, 5.394977, 7.125954, 5.634093)
desviacion_tipica_conocida <- sd(Sample1)

# Parámetros del intervalo de confianza
alpha <- 0.05  # Nivel de confianza del 95%
z_alpha_2 <- qnorm(1 - alpha / 2)  # Valor crítico de la distribución normal estándar
# Tamaño de la muestra
n <- length(Sample1)
# Calcular el valor inferior del intervalo de confianza
mean(Sample1) - z_alpha_2 * (desviacion_tipica_conocida / sqrt(n))
#27)
#Igual que antes pero +
mean(Sample1) + z_alpha_2 * (desviacion_tipica_conocida / sqrt(n))

#28)   NOSE HACERLO  ->   MAL
# Realizar el test z para la media
sample1_sd <- sd(Sample1)
resultado_prueba <- z.test(Sample1, sigma.x = NULL)
resultado_prueba$p.value     

z_test_result <- z.test(Sample1)
z_test_result$p.value

#29)
mean(Sample1)

#30)
resultado_t_test <- t.test(Sample1)

# Obtener el valor inferior del intervalo de confianza
resultado_t_test$conf.int[1]

#31)
resultado_t_test$conf.int[2]

#32) 
z_test_result <- z.test(Sample1, sigma.x = sd(Sample1))
z_test_result$p.value
#33)
mean(Sample1)

#34)
est_media <- mean(Sample1)
est_sd <- sd(Sample1)
est_var <- est_sd**2
n <- length(Sample1)
alpha <- 0.05
((n-1)*est_var)/qchisq(alpha/2,n-1,lower.tail = FALSE)

#35)
((n-1)*est_var)/qchisq(1-alpha/2,n-1,lower.tail = FALSE)

#36)
fit <- fitdistr(Sample1, "normal")
fit$estimate["var"]
var(Sample1)

#37#38#39)))  MAL
Sample1 <- c(4.681594, 3.718336, 4.986687, 4.505408, 2.638489, 4.729319, 3.476107, 3.300813, 6.069838, 4.026206, 3.465977, 5.32365, 3.67339, 5.40275, 5.021305, 4.57446, 3.285557, 5.394977, 7.125954, 5.634093)

Sample2 <- c("Municipal", "Municipal", "Cooperative", "Municipal", "Investor Owned",
             "Retail Power Marketer", "Municipal", "Municipal", "Municipal", "Municipal",
             "Municipal", "Cooperative", "Municipal", "Cooperative", "Municipal",
             "Municipal", "Municipal", "Retail Power Marketer", "Investor Owned", "Cooperative")

n_Coop <- sum(Sample2 == "Cooperative") #número de muestras de tipo "Cooperative"
n_NoCoop <- sum(Sample2 != "Cooperative") #número de muestras distintas a "Cooperative"
binom.test(x = c(n_Coop, n_NoCoop), alternative = "two.sided", conf.level = 0.95) 

#40) MAL
frecuencias <- table(Sample2)
mean(as.numeric(names(frecuencias)) * frecuencias)

#41 / 42)  41 BIEN - 42 MAL (POR LA CARA)
n <- length(Sample1);
bootR <- 1000;
set.seed(2023);
#library(boot); Descomentar si no incluíste esto al comienzo del código
boot.mean <- function(data, i) {
  return(mean(data[i]))
};
boot.results <- boot(Sample1, boot.mean, R = bootR);
IC <- boot.ci(boot.samples, conf = 0.95, type = c("norm"));
print(IC)  #Mirar si ha dado error por el espacio

#43)
boot.results$t0  #Punto medio valores anteriores

#44 / 45)
bootR <- 1000;
set.seed(2023);
boot_var <- function(data, indices) {
  var(data[indices])
}
boot_results <- boot(Sample1, boot_var, R = bootR)
IC <- boot.ci(boot_results, conf = 0.95, type = c("norm"))
print(IC)

#46) FALLA NO TIENE QUE SER EL PUNTO MEDIO SINO LA VARIANZA(No se hacerlo)
boot_results$t0

#47 #48))
bootR <- 1000;
set.seed(2023);
boot_prop <- function(data, index){
  prop <- sum(data[index] == "Municipal")/length(index)
  return(prop)
}
boot_results <- boot(data = Sample2, statistic = boot_prop, R = bootR)
boot_IC <- boot.ci(boot_results, conf = 0.95, type = c("norm"))
print(boot_IC)

#49)
boot_results  #La original, sin desviaciones

#50 #51))
t.test(Sample1[Sample2 == 'Municipal'],Sample1[Sample2 != 'Municipal'])

#52)
t.test <- t.test(Sample1[Sample2 == 'Municipal'],Sample1[Sample2 != 'Municipal'])
t.test$p.value
#53)
t.test$estimate[1]-t.test$estimate[2]

#54 #55 #56)))
result <- BSDA::z.test(Sample1[Sample2 == 'Municipal'],Sample1[Sample2 != 'Municipal'],
             sigma.x = sd(Sample1[Sample2 == 'Municipal']),
             sigma.y = sd(Sample1[Sample2 !='Municipal']))
result$p.value

#57)
result$estimate[1]-result$estimate[2]

#58 #59))
var.test(Sample1[Sample2 == 'Municipal'],Sample1[Sample2 != 'Municipal'])

#60) 
# Datos de muestra
Sample1 <- c(4.681594, 3.718336, 4.986687, 4.505408, 2.638489, 4.729319, 3.476107, 3.300813, 6.069838, 4.026206, 3.465977, 5.32365, 3.67339, 5.40275, 5.021305, 4.57446, 3.285557, 5.394977, 7.125954, 5.634093)
Sample2 <- c("Municipal", "Municipal", "Cooperative", "Municipal", "Investor Owned", "Retail Power Marketer", "Municipal", "Municipal", "Municipal", "Municipal", "Municipal", "Cooperative", "Municipal", "Cooperative", "Municipal", "Municipal", "Municipal", "Retail Power Marketer", "Investor Owned", "Cooperative")

# Separar muestras por tipo
sample_municipal <- Sample1[Sample2 == "Municipal"]
sample_no_municipal <- Sample1[Sample2 != "Municipal"]

# Realizar F-test
f_test_result <- var.test(sample_municipal, sample_no_municipal)

# Obtener el valor p
p_value <- f_test_result$p.value

# Imprimir el valor p
print(p_value)

#61)
# Datos de muestra
Sample1 <- c(4.681594, 3.718336, 4.986687, 4.505408, 2.638489, 4.729319, 3.476107, 3.300813, 6.069838, 4.026206, 3.465977, 5.32365, 3.67339, 5.40275, 5.021305, 4.57446, 3.285557, 5.394977, 7.125954, 5.634093)
Sample2 <- c("Municipal", "Municipal", "Cooperative", "Municipal", "Investor Owned", "Retail Power Marketer", "Municipal", "Municipal", "Municipal", "Municipal", "Municipal", "Cooperative", "Municipal", "Cooperative", "Municipal", "Municipal", "Municipal", "Retail Power Marketer", "Investor Owned", "Cooperative")

# Separar muestras por tipo
sample_municipal <- Sample1[Sample2 == "Municipal"]
sample_no_municipal <- Sample1[Sample2 != "Municipal"]

# Realizar F-test
f_test_result <- var.test(sample_municipal, sample_no_municipal)

# Obtener el ratio estimado de varianzas
ratio_varianzas <- f_test_result$estimate

# Imprimir el ratio estimado de varianzas
print(ratio_varianzas)


#62)
# Datos de muestra
Utility_Type <- c("Municipal", "Municipal", "Cooperative", "Municipal", "Investor Owned", "Retail Power Marketer", "Municipal", "Municipal", "Municipal", "Municipal", "Municipal", "Cooperative", "Municipal", "Cooperative", "Municipal", "Municipal", "Municipal", "Retail Power Marketer", "Investor Owned", "Cooperative")
# Contar las ocurrencias de "No Municipal"
ocurrencias_no_municipal <- sum(Utility_Type != "Municipal")
# Contar el total de observaciones
total_observaciones <- length(Utility_Type)

# Parámetros de la distribución Beta a priori
a_priori <- 1
b_priori <- 1

# Actualizar los parámetros de la distribución Beta con las observaciones
a_posteriori <- a_priori + ocurrencias_no_municipal
b_posteriori <- b_priori + total_observaciones - ocurrencias_no_municipal

# Estimar la proporción muestral utilizando la media de la distribución Beta posterior
proporcion_estimada <- a_posteriori / (a_posteriori + b_posteriori)

# Imprimir la estimación de la proporción muestral
print(proporcion_estimada)


#DESDE AQUI TODO MAL

#63)

#64)

#65)

#66)

#67)

#68)

#FALTAN POR HACER BIEN 28,37,38,39,40,42,46,63,64,65,66,67,68



