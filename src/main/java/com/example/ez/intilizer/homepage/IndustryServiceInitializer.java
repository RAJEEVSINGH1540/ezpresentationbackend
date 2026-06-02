package com.example.ez.intilizer.homepage;

import com.example.ez.homepage.industry.entity.IndustryService;
import com.example.ez.homepage.industry.repository.IndustryServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
@RequiredArgsConstructor
public class IndustryServiceInitializer implements CommandLineRunner {

    private final IndustryServiceRepository repo;

    @Override
    public void run(String... args) {
        if (repo.count() == 0) {
            repo.saveAll(List.of(
                IndustryService.builder().sortOrder(1)
                    .label("Construction ERP Software")
                    .subtitle("Construction ERP Software")
                    .description("EZ Construction delivers a fully integrated ERP platform purpose-built for construction firms. Manage budgets, timelines, labour, and procurement — all from one unified dashboard.")
                    .feature("Real-time project cost tracking & budget control")
                    .imageUrl("https://images.unsplash.com/photo-1555949963-ff9fe0c870eb?w=600&q=80&fit=crop")
                    .active(true).build(),
                IndustryService.builder().sortOrder(2)
                    .label("Project Management")
                    .subtitle("Project Management")
                    .description("Plan, schedule, and execute every phase of your construction project with precision. Gantt charts, milestone tracking, and task assignment built right in.")
                    .feature("Gantt chart scheduling & milestone tracking")
                    .imageUrl("https://images.unsplash.com/photo-1517694712202-14dd9538aa97?w=600&q=80&fit=crop")
                    .active(true).build(),
                IndustryService.builder().sortOrder(3)
                    .label("Property Maintenance")
                    .subtitle("Property Maintenance")
                    .description("Schedule preventive maintenance, manage work orders, and keep your assets running at peak performance with automated maintenance workflows.")
                    .feature("Automated work order & maintenance scheduling")
                    .imageUrl("https://images.unsplash.com/photo-1461749280684-dccba630e2f6?w=600&q=80&fit=crop")
                    .active(true).build(),
                IndustryService.builder().sortOrder(4)
                    .label("Procurement & Supply Chain")
                    .subtitle("Procurement & Supply Chain")
                    .description("Streamline vendor management, purchase orders, and inventory control. Get real-time visibility into your supply chain from order to delivery.")
                    .feature("End-to-end vendor & purchase order management")
                    .imageUrl("https://images.unsplash.com/photo-1504384308090-c894fdcc538d?w=600&q=80&fit=crop")
                    .active(true).build(),
                IndustryService.builder().sortOrder(5)
                    .label("Finance & Accounting ERP")
                    .subtitle("Finance & Accounting ERP")
                    .description("Automate invoicing, payroll, tax compliance, and financial reporting. Get a live view of your company's financial health across every project.")
                    .feature("Automated invoicing, payroll & tax compliance")
                    .imageUrl("https://images.unsplash.com/photo-1498050108023-c5249f4df085?w=600&q=80&fit=crop")
                    .active(true).build()
            ));
        }
    }
}