# Devsu Banking System

## Arquitectura y patrones

### Diagrama de contexto (C4 Model)
Imagen

### Diagrama de contenedores (C4 Model)
Imagen

### Patrones
Cada servicio tiene implementado Clean Architecture. Adicionalmente, se utilizaron varios patrones de diseño, así como varios patrones de microservicios

### Transacciones Saga
Se utilizan transacciones Saga para: 
1. Apertura de una Cuenta (Completado y en fase de pruebas)
2. Registro de un Movimiento (Pendiente)

#### Apertura de cuenta
Imagen

#### Registro de un movimiento
Imagen


## API entry points

### Clientes
	- GET /clientes					
	Obtiene el listado de todos los clientes
	
	- GET /clientes/{customerId}
	Obtiene los datos del cliente
	
	- POST /clientes				
	Registra un nuevo cliente
	
	- PUT /clientes/{customerId}		
	Actualiza los datos de un cliente
	
	- DELETE /clientes/{customerId}	
	Elimina el registro de un cliente


### Cuentas
	- GET /cuentas
	Obtiene el listado de todas los cuentas
	
	- GET /cuentas/cliente/{customerId}
	Obtiene el listado de todas los cuentas de un cliente
	
	- GET /cuentas/{accountId}
	Obtiene los datos de la cuenta
	
	- POST 
	Se implementó la lógica de negocio para la creación de una cuenta (AccountUseCase). Se sugiere implementar la apertura de una cuenta para un cliente desde otro entry point desde el servicio de clientes, de acuerdo a la transacción saga diseñada:
        POST /clientes/{customerId}/cuentas
	
	- PUT	
    Se implementó la lógica de negocio para la actualización de la información de una cuenta (AccountUseCase), sin embargo, se sugiere no crear el entry point debido a que la información de una cuenta existente no debería ser modificada luego de ser creada
	
	- DELETE /{accountId}	
	Elimina el registro de una cuenta


### Movimientos
	- GET /movimientos/cuenta/{accountId}	
	Obtiene el listado de todos los movimientos de una cuenta
	
	- GET /movimientos/{movementId}
	Obtiene los datos de un movimiento
	
	- POST 
	Se implementó la lógica de negocio para el registro de un movimiento(MovementUseCase). Se sugiere implementar el registro de un movimiento de la cuenta de un cliente desde otro entry point desde el api rest de cuentas en el servicio de cuentas, de acuerdo a la transacción saga diseñada:
        POST /movimientos/cuenta/{accountId}
	
	- PUT 
	No se implementó la lógica de negocio para la actualización de la información de un movimiento (MovementUseCase) debido a que la información de un movimiento existente no debería ser modificada luego de ser creada 
	
	- DELETE /{movementId}	
	Elimina el registro de un movimiento
     