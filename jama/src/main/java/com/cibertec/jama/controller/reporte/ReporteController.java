package com.cibertec.jama.controller.reporte;

import com.cibertec.jama.service.reporte.ReporteService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/reporte")
    public String mainReporte() {
        return "reporte/reporte";
    }

    @GetMapping("/reporte/ventas-mensuales")
    public String ventasMensuales(Model model) {
        model.addAttribute("ventasMensuales", reporteService.ventasMensuales());
        return "reporte/ventas-mensuales";
    }

    @GetMapping("/reporte/top-productos")
    public String topProductos(Model model) {
        model.addAttribute("topMenus", reporteService.topMenus());
        model.addAttribute("topExtras", reporteService.topExtras());
        return "reporte/top-productos";
    }

    @GetMapping("/reporte/ventas-mensuales/csv")
    public void exportarCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=ventas_mensuales.csv");

        PrintWriter writer = response.getWriter();
        reporteService.generarCsvVentas(writer);
    }

    @GetMapping("/reporte/ventas-mensuales/excel")
    public void exportarExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=ventas_mensuales.xlsx");

        Workbook workbook = reporteService.generarExcelVentas();
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}