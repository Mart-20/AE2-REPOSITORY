package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<Empleado> empleados = new ArrayList<>();
    private static final List<Proveedor> proveedores = new ArrayList<>();
    private static final List<ProductoServicio> productosServicios = new ArrayList<>();
    private static final List<Factura> facturas = new ArrayList<>();

    public static void main(String[] args) {
        cargarDatosIniciales();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerInt("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> registrarCliente();
                case 2 -> registrarEmpleado();
                case 3 -> registrarProveedor();
                case 4 -> registrarProductoServicio();
                case 5 -> emitirFactura();
                case 6 -> registrarPago();
                case 7 -> mostrarFacturas();
                case 8 -> ordenarFacturasPorMonto();
                case 9 -> ordenarFacturasPorFecha();
                case 10 -> mostrarPersonas();
                case 11 -> guardarFacturas();
                case 12 -> mostrarFacturasGuardadas();
                case 0 -> System.out.println("\nPrograma finalizado. ¡Hasta luego!");
                default -> System.out.println("\nOpción inválida. Intente nuevamente.");
            }

            if (opcion != 0) pausar();
        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=================================================");
        System.out.println("        SISTEMA DE GESTIÓN COMERCIAL");
        System.out.println("=================================================");
        System.out.println(" 1. Registrar cliente");
        System.out.println(" 2. Registrar empleado");
        System.out.println(" 3. Registrar proveedor");
        System.out.println(" 4. Registrar producto / servicio");
        System.out.println(" 5. Emitir factura");
        System.out.println(" 6. Registrar pago");
        System.out.println(" 7. Mostrar facturas");
        System.out.println(" 8. Ordenar facturas por monto");
        System.out.println(" 9. Ordenar facturas por fecha");
        System.out.println("10. Mostrar personas");
        System.out.println("11. Guardar facturas");
        System.out.println("12. Leer facturas guardadas");
        System.out.println(" 0. Salir");
        System.out.println("=================================================");
    }

    private static void cargarDatosIniciales() {
        Departamento ventas = new Departamento("Ventas", 100000, null);

        Empleado empleado = new Empleado(
                "Jeremias", "Domicilio", "DNI123", "1234567890",
                5000, PuestoEmpleado.ADMINISTRATIVO, "01-01-2023");
        empleado.setDepartamento(ventas);
        empleados.add(empleado);

        Cliente cliente = new Cliente(
                "Elias", "Domicilio", "DNI789", "5555555555",
                10000, CategoriaCliente.REGULAR);
        clientes.add(cliente);

        Proveedor proveedor = new Proveedor(
                "Proveedor San Jorge", "Domicilio", "DNI111", "1111111111",
                "Proveedor de Productos", "X123");
        proveedores.add(proveedor);

        ProductoServicio producto = new ProductoServicio(
                "P001", "Laptop", 1500, TipoItem.PRODUCTO, proveedor);
        ProductoServicio servicio = new ProductoServicio(
                "S001", "Servicio técnico", 500, TipoItem.SERVICIO, proveedor);

        proveedor.agregarItem(producto);
        proveedor.agregarItem(servicio);
        productosServicios.add(producto);
        productosServicios.add(servicio);
    }

    private static void registrarCliente() {
        System.out.println("\n--- REGISTRAR CLIENTE ---");
        String nombre = leerTexto("Nombre: ");
        String domicilio = leerTexto("Domicilio: ");
        String dni = leerTexto("DNI: ");
        String telefono = leerTexto("Teléfono: ");
        double limite = leerDouble("Límite de crédito: ");
        CategoriaCliente categoria = leerCategoria();

        clientes.add(new Cliente(nombre, domicilio, dni, telefono, limite, categoria));
        System.out.println("Cliente registrado correctamente.");
    }

    private static void registrarEmpleado() {
        System.out.println("\n--- REGISTRAR EMPLEADO ---");
        String nombre = leerTexto("Nombre: ");
        String domicilio = leerTexto("Domicilio: ");
        String dni = leerTexto("DNI: ");
        String telefono = leerTexto("Teléfono: ");
        double salario = leerDouble("Salario: ");
        PuestoEmpleado puesto = leerPuesto();
        String fecha = leerTexto("Fecha de ingreso: ");

        Departamento departamento = new Departamento("General", 50000, null);
        Empleado empleado = new Empleado(
                nombre, domicilio, dni, telefono, salario, puesto, fecha);
        empleado.setDepartamento(departamento);
        empleados.add(empleado);

        System.out.println("Empleado registrado correctamente.");
    }

    private static void registrarProveedor() {
        System.out.println("\n--- REGISTRAR PROVEEDOR ---");
        String nombre = leerTexto("Nombre: ");
        String domicilio = leerTexto("Domicilio: ");
        String dni = leerTexto("DNI: ");
        String telefono = leerTexto("Teléfono: ");
        String razonSocial = leerTexto("Razón social: ");
        String identificacion = leerTexto("Número de identificación fiscal: ");

        proveedores.add(new Proveedor(
                nombre, domicilio, dni, telefono, razonSocial, identificacion));

        System.out.println("Proveedor registrado correctamente.");
    }

    private static void registrarProductoServicio() {
        if (proveedores.isEmpty()) {
            System.out.println("Primero debe registrar un proveedor.");
            return;
        }

        System.out.println("\n--- REGISTRAR PRODUCTO / SERVICIO ---");
        System.out.println("1. Producto");
        System.out.println("2. Servicio");
        int tipo = leerInt("Tipo: ");

        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo inválido.");
            return;
        }

        String codigo = leerTexto("Código: ");
        String nombre = leerTexto("Nombre: ");
        double precio = leerDouble("Precio: ");
        TipoItem tipoItem = (tipo == 1) ? TipoItem.PRODUCTO : TipoItem.SERVICIO;

        mostrarProveedores();
        int indice = leerInt("Seleccione proveedor: ") - 1;
        if (indice < 0 || indice >= proveedores.size()) {
            System.out.println("Proveedor inválido.");
            return;
        }

        Proveedor proveedor = proveedores.get(indice);
        ProductoServicio item = new ProductoServicio(
                codigo, nombre, precio, tipoItem, proveedor);

        productosServicios.add(item);
        proveedor.agregarItem(item);

        System.out.println("Producto/servicio registrado correctamente.");
    }

    private static void emitirFactura() {
        if (clientes.isEmpty() || empleados.isEmpty() || productosServicios.isEmpty()) {
            System.out.println("Debe existir al menos un cliente, un empleado y un producto/servicio.");
            return;
        }

        System.out.println("\n--- EMITIR FACTURA ---");
        int numero = leerInt("Número de factura: ");
        String fecha = leerTexto("Fecha (dd/MM/yyyy): ");

        mostrarClientes();
        int clienteIndex = leerInt("Seleccione cliente: ") - 1;
        if (clienteIndex < 0 || clienteIndex >= clientes.size()) {
            System.out.println("Cliente inválido.");
            return;
        }

        mostrarEmpleados();
        int empleadoIndex = leerInt("Seleccione empleado: ") - 1;
        if (empleadoIndex < 0 || empleadoIndex >= empleados.size()) {
            System.out.println("Empleado inválido.");
            return;
        }

        Factura factura = new Factura(
                numero, fecha, clientes.get(clienteIndex), empleados.get(empleadoIndex));

        while (true) {
            mostrarProductosServicios();
            int itemIndex = leerInt("Seleccione ítem (0 para terminar): ") - 1;

            if (itemIndex == -1) break;

            if (itemIndex >= 0 && itemIndex < productosServicios.size()) {
                factura.agregarItem(productosServicios.get(itemIndex));
                System.out.println("Ítem agregado.");
            } else {
                System.out.println("Ítem inválido.");
            }
        }

        try {
            factura.emitir();
            facturas.add(factura);
            System.out.println("Factura registrada correctamente.");
        } catch (FacturaSinItemsException e) {
            System.out.println("No se pudo emitir: " + e.getMessage());
        }
    }

    private static void registrarPago() {
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas cargadas.");
            return;
        }

        System.out.println("\n--- REGISTRAR PAGO ---");
        mostrarFacturas();
        int numero = leerInt("Número de factura: ");

        Factura factura = buscarFactura(numero);
        if (factura == null) {
            System.out.println("Factura no encontrada.");
            return;
        }

        double saldo = factura.saldoPendiente();
        if (saldo <= 0) {
            System.out.println("La factura ya está cancelada.");
            return;
        }

        System.out.printf("Saldo pendiente: $%.2f%n", saldo);
        double monto = leerDouble("Monto del pago: ");
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor a cero.");
            return;
        }

        String fecha = leerTexto("Fecha del pago (dd/MM/yyyy): ");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta");
        System.out.println("3. Transferencia");
        int forma = leerInt("Forma de pago: ");

        try {
            Pago pago;
            switch (forma) {
                case 1 -> pago = new PagoEfectivo(monto, fecha);
                case 2 -> {
                    String tarjeta = leerTexto("Número de tarjeta: ");
                    pago = new PagoTarjeta(monto, fecha, tarjeta);
                }
                case 3 -> {
                    String cbu = leerTexto("CBU: ");
                    pago = new PagoTransferencia(monto, fecha, cbu);
                }
                default -> {
                    System.out.println("Forma de pago inválida.");
                    return;
                }
            }

            factura.registrarPago(pago);
            System.out.println("Pago registrado correctamente.");
        } catch (PagoExcedeDeudaException | StringIndexOutOfBoundsException e) {
            System.out.println("No se pudo registrar el pago: " + e.getMessage());
        }
    }

    private static void mostrarFacturas() {
        System.out.println("\n--- FACTURAS ---");
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas cargadas.");
            return;
        }

        for (Factura factura : facturas) {
            factura.mostrarDetalle();
            System.out.println();
        }
    }

    private static void ordenarFacturasPorMonto() {
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas para ordenar.");
            return;
        }

        List<Factura> copia = new ArrayList<>(facturas);
        copia.sort(null);

        System.out.println("\n--- FACTURAS ORDENADAS POR MONTO ---");
        for (Factura factura : copia) {
            System.out.println(factura);
        }
    }

    private static void ordenarFacturasPorFecha() {
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas para ordenar.");
            return;
        }

        List<Factura> copia = new ArrayList<>(facturas);
        copia.sort(new FacturaPorFechaComparator());

        System.out.println("\n--- FACTURAS ORDENADAS POR FECHA ---");
        for (Factura factura : copia) {
            System.out.println(factura);
        }
    }

    private static void mostrarPersonas() {
        System.out.println("\n--- PERSONAS REGISTRADAS ---");
        for (Cliente cliente : clientes) cliente.mostrarInfo();
        for (Empleado empleado : empleados) empleado.mostrarInfo();
        for (Proveedor proveedor : proveedores) proveedor.mostrarInfo();
    }

    private static void guardarFacturas() {
        Empresa empresa = new Empresa();
        for (Factura factura : facturas) {
            empresa.agregarFactura(factura);
        }
        empresa.guardarFacturas();
    }

    private static void mostrarFacturasGuardadas() {
        Empresa empresa = new Empresa();
        empresa.mostrarFacturasGuardadas();
    }

    private static Factura buscarFactura(int numero) {
        for (Factura factura : facturas) {
            if (factura.getNumero() == numero) return factura;
        }
        return null;
    }

    private static void mostrarClientes() {
        System.out.println("\nClientes:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNombre());
        }
    }

    private static void mostrarEmpleados() {
        System.out.println("\nEmpleados:");
        for (int i = 0; i < empleados.size(); i++) {
            System.out.println((i + 1) + ". " + empleados.get(i).getNombre());
        }
    }

    private static void mostrarProveedores() {
        System.out.println("\nProveedores:");
        for (int i = 0; i < proveedores.size(); i++) {
            System.out.println((i + 1) + ". " + proveedores.get(i).getRazonSocial());
        }
    }

    private static void mostrarProductosServicios() {
        System.out.println("\nProductos / Servicios:");
        for (int i = 0; i < productosServicios.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, productosServicios.get(i));
        }
    }

    private static CategoriaCliente leerCategoria() {
        System.out.println("1. Regular");
        System.out.println("2. Premium");
        System.out.println("3. Corporativo");
        int opcion = leerInt("Categoría: ");
        return switch (opcion) {
            case 2 -> CategoriaCliente.PREMIUM;
            case 3 -> CategoriaCliente.CORPORATIVO;
            default -> CategoriaCliente.REGULAR;
        };
    }

    private static PuestoEmpleado leerPuesto() {
        System.out.println("1. Administrativo");
        System.out.println("2. Técnico");
        System.out.println("3. Gerente");
        int opcion = leerInt("Puesto: ");
        return switch (opcion) {
            case 2 -> PuestoEmpleado.TECNICO;
            case 3 -> PuestoEmpleado.GERENTE;
            default -> PuestoEmpleado.ADMINISTRATIVO;
        };
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static int leerInt(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(leerTexto(mensaje).replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private static void pausar() {
        System.out.println("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }
}
