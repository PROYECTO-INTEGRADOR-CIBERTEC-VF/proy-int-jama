package com.cibertec.jama.service.reporte;

import com.cibertec.jama.repositories.pedido.PedidoExtraRepository;
import com.cibertec.jama.repositories.pedido.PedidoItemRepository;
import com.cibertec.jama.repositories.venta.VentaKardexRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.List;

@Service
public class ReporteService {

    private final VentaKardexRepository ventaKardexRepository;
    private final PedidoItemRepository pedidoItemRepository;
    private final PedidoExtraRepository pedidoExtraRepository;

    public ReporteService(VentaKardexRepository ventaKardexRepository,
                          PedidoItemRepository pedidoItemRepository,
                          PedidoExtraRepository pedidoExtraRepository) {
        this.ventaKardexRepository = ventaKardexRepository;
        this.pedidoItemRepository = pedidoItemRepository;
        this.pedidoExtraRepository = pedidoExtraRepository;
    }

    public List<Object[]> ventasMensuales() {
        return ventaKardexRepository.reporteVentasMensuales();
    }

    public List<Object[]> topMenus() {
        return pedidoItemRepository.reporteTopMenus();
    }

    public List<Object[]> topExtras() {
        return pedidoExtraRepository.reporteTopExtras();
    }

    public void generarCsvVentas(PrintWriter writer) {
        writer.println("Anio,Mes,Cantidad Ventas,Total");

        for (Object[] fila : ventasMensuales()) {
            writer.println(fila[0] + "," + fila[1] + "," + fila[2] + "," + fila[3]);
        }
    }

    public Workbook generarExcelVentas() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Ventas Mensuales");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Año");
        header.createCell(1).setCellValue("Mes");
        header.createCell(2).setCellValue("Cantidad de ventas");
        header.createCell(3).setCellValue("Total");

        int rowNum = 1;

        for (Object[] fila : ventasMensuales()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(String.valueOf(fila[0]));
            row.createCell(1).setCellValue(String.valueOf(fila[1]));
            row.createCell(2).setCellValue(String.valueOf(fila[2]));
            row.createCell(3).setCellValue(String.valueOf(fila[3]));
        }

        return workbook;
    }
}