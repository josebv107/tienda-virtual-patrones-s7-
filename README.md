# Virtual Store - Patrones de diseño
Este proyecto implementa una tienda virtual desde consola en Java aplicando los tres patrones de diseño vistos en clase: 
Strategy, Adapter y Observer.

## Descripción del problema
El sistema simula una tienda virtual donde un cliente puede: agregar productos a un carrito de compras, ver el contenido
del carrito y el descuento aplicado (si lo hay), elegir un método de pago (simulación) y finalmente confirmar la compra 
y recibir notificaciones automáticas.

Para realizarlo, el reto fue que cada una de estas funcionalidades tenía un problema distinto que necesitaban una solución
flexible y escalable, sin que si hay cambios estos rompan las demás.

## Patrones aplicados
### Strategy (Descuentos)
El problema es que los descuentos pueden cambiar, a veces es un porcentaje, otras un monto fijo, otras veces ni hay descuento.
El propósito de la aplicación de Strategy es que, por ejemplo, si escribía un if o switch dentro del carrito para cada caso, 
cada vez que quiera agregar un nuevo tipo de descuento tendría que modificar el carrito cada vez.

Entonces para esto se creó la clase `DiscountStrategy` con un método `applyDiscount(double total)`, gracias a esto cada 
tipo de descuento es una clase separada que implementa esa interfaz. Se puede cambiar la estrategia en cualquier momento sin tocar el carrito
(2do principio de SOLID).

Entonces podemos decir que el patrón Strategy es útil en estos casos, cuando se tiene un comportamiento que puede variar 
y quieres que esa variación no afecte a la clase que lo usa.

### Adapter (Métodos de pago)
El problema es que se quería integrar Paypal como método de pago, pero el servicio externo de Paypal tiene su propio método
`makePayment(String currency, double amount)` que es distinto al `contrato pay(double amount)` que usa la tienda. No se podía 
modificar el servicio externo porque en la vida real es una librería de terceros.

Para solucionarlo creé la interfaz `PaymentProcessor` con el método `pay(double amount)`.
Luego creé `PayPalAdapter` que implementa `PaymentProcessor` y por dentro
traduce la llamada al método del servicio externo. La tienda solo habla con
`PaymentProcessor` y no sabe que por debajo hay un servicio diferente.

Entonces como aprendizaje podemos decir que el patrón Adapter actúa como un traductor entre dos interfaces que no pueden modificarse, nos es util
cuando se trabaja con código externo.

### Observer (Notificaciones de compra)

El problema es que al confirmar una compra varios sistemas necesitan reaccionar:
enviar un correo al cliente, actualizar el inventario y notificar al administrador.
Si ponía esas llamadas directamente en el servicio de pedidos, cada vez que
quisiera agregar un nuevo sistema tendría que modificar esa clase.

Para solucionarlo creé la interfaz `OrderObserver` con el método `update(String message)`.
Cada sistema de notificación implementa esa interfaz. El `OrderService` mantiene
una lista de observadores y al confirmar la compra los recorre y notifica a todos.
Para agregar un nuevo sistema solo creo una clase nueva, sin tocar el servicio.

Lo que aprendí es que el patrón Observer es ideal cuando un evento en un objeto
debe desencadenar acciones en otros objetos sin que estén acoplados entre sí.

## Estructura del proyecto
<img width="330" height="559" alt="image" src="https://github.com/user-attachments/assets/14a0303d-7646-47e5-980b-1a38d6390fdd" />

## Salida en consola
<img width="811" height="494" alt="image" src="https://github.com/user-attachments/assets/a004c32d-c3f2-440c-859e-3ca62a72c3e5" />

