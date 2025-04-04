#CONTRASTES PARAMETRICOS
#(quantile(), t.test(), var.test()).

Sample1 <- c (4.681594, 3.718336, 4.986687, 4.505408, 2.638489, 4.729319, 3.476107, 3.300813, 6.069838, 4.026206, 3.465977, 5.32365, 3.67339, 5.40275, 5.021305, 4.57446, 3.285557, 5.394977, 7.125954, 5.634093)
Sample2 <- c(4.578513, 4.497096, 3.852297, 5.490694, 5.576659, 4.277838, 5.576177, 5.995185, 5.14085, 5.284471, 4.882974, 4.74928, 4.470396, 7.243947, 5.102643, 4.643117, 6.493885, 4.9389, 5.771786, 5.495522)

#1)
Q1 <- quantile(Sample1, 0.25)

#2)
Q3 <- quantile(Sample1, 0.75)

#3(t) / 4(df) / 5(p-value)) 
t.test(Sample1, mu = Q1, alternative = "less")

#6(t) / 7(df) / 8(p-value)) 
t.test(Sample1, mu = Q1, alternative = "greater")

#9(t) / 10(df) / 11(p-value)) 
t.test(Sample1, mu = Q1, alternative = "two.sided")

#12(t) / 13(df) / 14(p-value)) 
t.test(Sample1, mu = Q3, alternative = "less")

#15(t) / 16(df) / 17(p-value)) 
t.test(Sample1, mu = Q3, alternative = "greater")

#18(t) / 19(df) / 20(p-value)) 
t.test(Sample1, mu = Q3, alternative = "two.sided")

#21(d) / 22(df) / 23(p-value)) ERROR -> en valor d y p-value
Sample1 <- c (4.681594, 3.718336, 4.986687, 4.505408, 2.638489, 4.729319, 3.476107, 3.300813, 6.069838, 4.026206, 3.465977, 5.32365, 3.67339, 5.40275, 5.021305, 4.57446, 3.285557, 5.394977, 7.125954, 5.634093)
sample1.1 <- rnorm(length(Sample1))
scale_factor <- sqrt(1 / var(sample1.1))
sample1.2 <- sample1.1 * scale_factor
var(sample1.2)

var.test(Sample1,sample1.2 , alternative = "less")

(var(Sample1) - 1) / sqrt(var(Sample1))

#24(t) / 25(df -> redondear) / 26(p-value))  
t.test(Sample1, Sample2)#two sided indica mu1 != mu2(puedes no ponerlo ya que es es el predeterminado)

#27(t) / 28(df) / 29(p-value))
var.test(Sample1, Sample2)

Sample3 <- c(4.681594, 3.718336, 4.986687, 4.505408, 2.638489, 4.729319, 3.476107, 3.300813, 6.069838, 4.026206, 3.465977, 5.32365, 3.67339, 5.40275, 5.021305, 4.57446, 3.285557, 5.394977, 7.125954, 5.634093)

#30 estadistico pearson / 31 n clases / 32 p-value)
pearson.test(Sample3)
str(pearson.test(Sample3)) #str saca las n clases

#33 / 34 / )
ks.test(Sample3, "pnorm", mean(Sample3), sd(Sample3))


Sample4 <- c("Municipal", "Municipal", "Municipal", "Cooperative", "PoliticalSubdivision", 
             "RetailPowerMarketer", "Cooperative", "PoliticalSubdivision", "Municipal", 
             "Cooperative", "PoliticalSubdivision", "Municipal", "Municipal", 
             "RetailPowerMarketer", "Municipal", "RetailPowerMarketer", "Municipal", 
             "Municipal", "Cooperative", "Cooperative")
#35(chi quare) / 36(df) / 37(p-value))
observed <- table(Sample4)    # Cuenta frecuencias table4
n <- length(unique(Sample4))  # Contar valores unicos
expected <- rep(1/n,n)        # Crear vector con las aparencias unicas
chisq.test(observed, p = expected)

## OTRA FORMA
chisq.test(table(Sample4), p = rep(1/length(unique(Sample4)), length(unique(Sample4))))

Sample5 <- c(4.681594, 3.718336, 4.986687, 4.505408, 2.638489, 4.729319, 
             3.476107, 3.300813, 6.069838, 4.026206, 3.465977, 5.32365, 
             3.67339, 5.40275, 5.021305, 4.57446, 3.285557, 5.394977, 
             7.125954, 5.634093)

Sample6 <- c(4.578513, 4.497096, 3.852297, 5.490694, 5.576659, 4.277838, 
             5.576177, 5.995185, 5.14085, 5.284471, 4.882974, 4.74928, 
             4.470396, 7.243947, 5.102643, 4.643117, 6.493885, 4.9389, 
             5.771786, 5.495522)

#38(estadistico) / 39(estimacion media) / 40(p-value)) 
wilcox.test(Sample5, Sample6, alternative = "two.sided", mu = 0, paired = FALSE, conf.int = 0.95)
str(wilcox.test(Sample5, Sample6))

########################################################################################
######## APARTADO B ###########################################################


Sources.Total <- c(3553, 580.6, 12730.8, 3187.3, 464, 2010.7, 477, 427, 148254, 1496.6, 345, 21058, 862.9, 26588.4, 10730.3, 4817.9, 215.8, 22394, 1373998, 36882)
Uses.Total <- c(612, 370, 5165, 986, 114, 1, 237, 250, 55919, 738, 228, 5282, 466, 7650, 3436, 1997, 168, 16445, 503198, 16263)
Revenue.Total <- c(48038, 5227, 96980, 32018, 434, 53618, 2992, 1998, 1174458, 10621, 2923, 210692, 4713, 252783, 105027, 37536, 1929, 248299, 13364533, 430618)

#41(DW) ,42(p-value) )
dwtest(lm(Revenue.Total ~ (Sources.Total + Uses.Total)), alternative = "two.sided")


Uses.Retail <- c(4.681594, 3.718336, 4.986687, 4.505408, 2.638489, 4.729319, 3.476107, 3.300813, 6.069838, 4.026206, 3.465977, 5.32365, 3.67339, 5.40275, 5.021305, 4.57446, 3.285557, 5.394977, 7.125954, 5.634093)
Uses.Losses <- c(3.047275, 1.60206, 3.683497, 3.360025, 1.968483, 0, 2.50515, 0, 4.583131, 3.130977, 0, 4.136245, 0, 4.126716, 2.819544, 3.11059, 2.30963, 0, 5.402688, 3.546666)

# 43(beta0), 44(beta1))
modelo <- lm(Uses.Losses ~ Uses.Retail)
modelo$coef[1] #BETA 0
modelo$coef[1] #BETA 1

# 45, 46, 47, 48, 50
summary(modelo)

summary(modelo)$r.squared #45

summary(modelo)$coef[1,4]#46
summary(modelo)$coefficients[1, "Pr(>|t|)"]

summary(modelo)$coef[2,4]#47

summary(modelo)$fstatistic[1]#48

summary(modelo)$df[1]#49
df_regression <- summary(modelo)$fstatistic[2]
df_residual <- summary(modelo)$fstatistic[3]
df_regression+df_residual
#50
min_retail <- min(Uses.Retail) 
prediction <- predict(modelo, newdata = data.frame(Uses.Retail = min_retail))
#51
#52
#53
mean_retail <- mean(Uses.Retail) 
predict(modelo, newdata = data.frame(Uses.Retail = mean_retail))
#54 55
predict(modelo, newdata = data.frame(Uses.Retail = mean(Uses.Retail)), interval = "prediction")

#56
max_retail <- max(Uses.Retail)
predict(modelo, newdata = data.frame(Uses.Retail = max_retail))
#57
#58




###############################################################################################################
#ERRORES: 21, 23,46,48,51,52,54,55,57,58
