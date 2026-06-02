// src/main/java/com/cms/initializer/ServiceDataInitializer.java
package com.example.ez.intilizer;

import com.example.ez.services.dto.servicedetailpage.*;
import com.example.ez.services.service.ServiceDetailCmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ServiceDataInitializer implements CommandLineRunner {

    private final ServiceDetailCmsService svc;

    @Override
    public void run(String... args) {
        seedAll();
    }

    private void seedAll() {
        seedErp();
        seedHrms();
        seedPayroll();
        seedCrm();
        seedInventory();
        seedAccounting();
        seedAttendance();
        seedAutomation();
        seedAnalytics();
        seedMobile();
        log.info("✅ Service data initializer complete.");
    }

    // ── Skip if already exists ───────────────────────────────────────
    private boolean skip(String id) {
        if (svc.serviceExists(id)) {
            log.info("⏭ Skipping '{}' — already seeded.", id);
            return true;
        }
        return false;
    }

    // ════════════════════════════════════════════════════════════════
    // ERP
    // ════════════════════════════════════════════════════════════════
    private void seedErp() {
        if (skip("erp")) return;
        log.info("🌱 Seeding ERP...");

        svc.saveHero("erp", ServiceHeroDto.builder()
            .serviceId("erp")
            .title("ERP Software")
            .subtitle("Enterprise Resource Planning")
            .tagline("One Platform. Every Business Function. Zero Compromise.")
            .description("Our Enterprise Resource Planning software unifies every critical business function into a single intelligent platform — from finance and procurement to operations and executive reporting.")
            .heroImage("https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=1200&q=80")
            .color("#1a56e8")
            .tag("Core Platform")
            .build());

        svc.saveOverview("erp", new ServiceOverviewDto(
            "Built for the complexity of modern enterprises, our ERP platform connects every department, automates critical workflows, and delivers real-time intelligence across your entire organization. From a single dashboard, executives gain complete visibility while operations teams work faster than ever before."));

        svc.saveStats("erp", List.of(
            ServiceStatDto.builder().value("60%").label("Reduction in Operational Costs").build(),
            ServiceStatDto.builder().value("3x").label("Faster Financial Closing").build(),
            ServiceStatDto.builder().value("99.9%").label("Data Accuracy Rate").build(),
            ServiceStatDto.builder().value("500+").label("Enterprise Deployments").build()
        ));

        svc.saveModules("erp", List.of(
            ServiceModuleDto.builder().icon("💰").title("Financial Management")
                .desc("Complete general ledger, accounts payable/receivable, budgeting, and multi-currency financial consolidation across all entities.").build(),
            ServiceModuleDto.builder().icon("🛒").title("Procurement & Purchasing")
                .desc("Automated purchase requisitions, vendor management, RFQ processing, and three-way matching for audit-ready procurement.").build(),
            ServiceModuleDto.builder().icon("🏭").title("Operations Management")
                .desc("Production scheduling, resource allocation, capacity planning, and real-time shop floor visibility.").build(),
            ServiceModuleDto.builder().icon("📊").title("Executive Reporting")
                .desc("Live dashboards, KPI tracking, board-level reports, and AI-powered forecasting for strategic decision-making.").build(),
            ServiceModuleDto.builder().icon("🔗").title("Supply Chain")
                .desc("End-to-end supply chain visibility with demand forecasting, supplier collaboration, and logistics coordination.").build(),
            ServiceModuleDto.builder().icon("📋").title("Project Management")
                .desc("Project costing, milestone tracking, resource utilization, and profitability analysis integrated with finance.").build()
        ));

        svc.saveBenefits("erp", List.of(
            ServiceBenefitDto.builder().title("Unified Data Architecture")
                .desc("Eliminate data silos with a single source of truth across every department. Real-time synchronization ensures every team works with accurate, up-to-date information.")
                .image("https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("Intelligent Automation")
                .desc("Automate repetitive workflows, approvals, and reconciliations. Reduce manual data entry by 80% and eliminate costly human errors across financial and operational processes.")
                .image("https://images.unsplash.com/photo-1518186285589-2f7649de83e0?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("Executive Intelligence")
                .desc("Access real-time analytics, AI-powered insights, and predictive forecasting that transform raw data into strategic advantage for leadership teams.")
                .image("https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=800&q=80").build()
        ));

        svc.saveFeatures("erp", List.of(
            ServiceFeatureDto.builder().feature("Multi-entity financial consolidation").build(),
            ServiceFeatureDto.builder().feature("Real-time general ledger").build(),
            ServiceFeatureDto.builder().feature("Automated bank reconciliation").build(),
            ServiceFeatureDto.builder().feature("Vendor portal & e-procurement").build(),
            ServiceFeatureDto.builder().feature("Fixed asset management").build(),
            ServiceFeatureDto.builder().feature("Tax compliance engine").build(),
            ServiceFeatureDto.builder().feature("Custom approval workflows").build(),
            ServiceFeatureDto.builder().feature("Role-based access control").build(),
            ServiceFeatureDto.builder().feature("Mobile executive dashboard").build(),
            ServiceFeatureDto.builder().feature("API & third-party integrations").build(),
            ServiceFeatureDto.builder().feature("Audit trail & compliance logs").build(),
            ServiceFeatureDto.builder().feature("Multi-currency & multi-language").build()
        ));

        svc.saveTechSpecs("erp", List.of(
            ServiceTechSpecDto.builder().label("Deployment").value("Cloud, On-Premise, Hybrid").build(),
            ServiceTechSpecDto.builder().label("Uptime SLA").value("99.9%").build(),
            ServiceTechSpecDto.builder().label("Data Encryption").value("AES-256").build(),
            ServiceTechSpecDto.builder().label("Compliance").value("SOC 2, ISO 27001").build(),
            ServiceTechSpecDto.builder().label("Integrations").value("200+ Pre-built").build(),
            ServiceTechSpecDto.builder().label("Implementation").value("14–30 Days").build()
        ));

        svc.saveCta("erp", ServiceCtaDto.builder()
            .badgeText("Ready to Transform?")
            .heading("Deploy ERP Software In 14 Days.")
            .subText("Join hundreds of enterprises running ERP on our platform. Get a personalised demo built around your specific workflows and requirements.")
            .buttonLabel("Book Free Demo")
            .trustPoints(List.of("✓ No setup fees", "✓ 14-day go-live", "✓ Dedicated support", "✓ SOC 2 certified"))
            .roiLabel("ROI Timeline").roiValue("3 Months")
            .goLiveLabel("Go-Live").goLiveValue("14 Days").goLiveSubtext("Guided setup")
            .build());

        svc.saveRelated("erp", List.of(
            ServiceRelatedDto.builder().relatedServiceId("hrms").build(),
            ServiceRelatedDto.builder().relatedServiceId("accounting").build(),
            ServiceRelatedDto.builder().relatedServiceId("inventory").build()
        ));
    }

    // ════════════════════════════════════════════════════════════════
    // HRMS
    // ════════════════════════════════════════════════════════════════
    private void seedHrms() {
        if (skip("hrms")) return;
        log.info("🌱 Seeding HRMS...");

        svc.saveHero("hrms", ServiceHeroDto.builder()
            .serviceId("hrms")
            .title("HRMS Software")
            .subtitle("Human Resource Management System")
            .tagline("Manage Your Entire Workforce. From Hire to Retire.")
            .description("A comprehensive Human Resource Management System designed for enterprises managing complex, multi-location workforces. Cover every stage of the employee lifecycle with intelligence.")
            .heroImage("https://images.unsplash.com/photo-1522202176988-66273c2fd55f?w=1200&q=80")
            .color("#7c3aed")
            .tag("HR Suite")
            .build());

        svc.saveOverview("hrms", new ServiceOverviewDto(
            "Our HRMS platform transforms how enterprises manage people. From intelligent onboarding and performance management to compliance automation and workforce analytics, every HR process becomes faster, smarter, and audit-ready."));

        svc.saveStats("hrms", List.of(
            ServiceStatDto.builder().value("3x").label("Faster Onboarding").build(),
            ServiceStatDto.builder().value("91%").label("Employee Satisfaction").build(),
            ServiceStatDto.builder().value("100%").label("Compliance Coverage").build(),
            ServiceStatDto.builder().value("50K+").label("Employees Managed").build()
        ));

        svc.saveModules("hrms", List.of(
            ServiceModuleDto.builder().icon("🚀").title("Onboarding & Offboarding")
                .desc("Digital onboarding workflows, document collection, equipment provisioning, and structured offboarding with knowledge transfer protocols.").build(),
            ServiceModuleDto.builder().icon("🎯").title("Performance Management")
                .desc("OKR and KPI tracking, 360-degree reviews, continuous feedback, calibration tools, and performance-linked compensation planning.").build(),
            ServiceModuleDto.builder().icon("📅").title("Leave & Absence")
                .desc("Multi-policy leave management, accrual tracking, approval workflows, and real-time team availability calendars.").build(),
            ServiceModuleDto.builder().icon("🎓").title("Learning & Development")
                .desc("LMS integration, training scheduling, skill gap analysis, certification tracking, and career development paths.").build(),
            ServiceModuleDto.builder().icon("📈").title("Workforce Analytics")
                .desc("Attrition prediction, headcount planning, diversity metrics, compensation benchmarking, and department-level insights.").build(),
            ServiceModuleDto.builder().icon("⚖️").title("Compliance Engine")
                .desc("Statutory compliance automation, labour law updates, audit-ready documentation, and regulatory reporting.").build()
        ));

        svc.saveBenefits("hrms", List.of(
            ServiceBenefitDto.builder().title("Complete Employee Lifecycle")
                .desc("Manage every touchpoint of the employee journey from offer letter to exit interview. Automated workflows ensure nothing falls through the cracks.")
                .image("https://images.unsplash.com/photo-1542744173-8e7e53415bb0?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("AI-Powered People Analytics")
                .desc("Predict attrition, identify high performers, and understand workforce trends before they become problems. Data-driven HR decisions at every level.")
                .image("https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("Compliance Without Complexity")
                .desc("Stay compliant with ever-changing labour laws automatically. Our compliance engine monitors regulations and updates your workflows in real time.")
                .image("https://images.unsplash.com/photo-1450101499163-c8848c66ca85?w=800&q=80").build()
        ));

        svc.saveFeatures("hrms", List.of(
            ServiceFeatureDto.builder().feature("Digital onboarding workflows").build(),
            ServiceFeatureDto.builder().feature("360-degree performance reviews").build(),
            ServiceFeatureDto.builder().feature("OKR & goal management").build(),
            ServiceFeatureDto.builder().feature("Automated leave management").build(),
            ServiceFeatureDto.builder().feature("Employee self-service portal").build(),
            ServiceFeatureDto.builder().feature("Org chart & directory").build(),
            ServiceFeatureDto.builder().feature("Succession planning").build(),
            ServiceFeatureDto.builder().feature("Compensation management").build(),
            ServiceFeatureDto.builder().feature("HR analytics dashboard").build(),
            ServiceFeatureDto.builder().feature("Document management").build(),
            ServiceFeatureDto.builder().feature("Labour law compliance").build(),
            ServiceFeatureDto.builder().feature("Multi-location HR management").build()
        ));

        svc.saveTechSpecs("hrms", List.of(
            ServiceTechSpecDto.builder().label("Deployment").value("Cloud SaaS").build(),
            ServiceTechSpecDto.builder().label("Uptime SLA").value("99.9%").build(),
            ServiceTechSpecDto.builder().label("Data Encryption").value("AES-256").build(),
            ServiceTechSpecDto.builder().label("Compliance").value("GDPR, SOC 2").build(),
            ServiceTechSpecDto.builder().label("Employee Capacity").value("Unlimited").build(),
            ServiceTechSpecDto.builder().label("Implementation").value("7–21 Days").build()
        ));

        svc.saveCta("hrms", ServiceCtaDto.builder()
            .badgeText("Ready to Transform?")
            .heading("Deploy HRMS Software In 14 Days.")
            .subText("Join hundreds of enterprises running HRMS on our platform. Get a personalised demo built around your specific workflows and requirements.")
            .buttonLabel("Book Free Demo")
            .trustPoints(List.of("✓ No setup fees", "✓ 14-day go-live", "✓ Dedicated support", "✓ SOC 2 certified"))
            .roiLabel("ROI Timeline").roiValue("3 Months")
            .goLiveLabel("Go-Live").goLiveValue("14 Days").goLiveSubtext("Guided setup")
            .build());

        svc.saveRelated("hrms", List.of(
            ServiceRelatedDto.builder().relatedServiceId("payroll").build(),
            ServiceRelatedDto.builder().relatedServiceId("attendance").build(),
            ServiceRelatedDto.builder().relatedServiceId("erp").build()
        ));
    }

    // ════════════════════════════════════════════════════════════════
    // PAYROLL
    // ════════════════════════════════════════════════════════════════
    private void seedPayroll() {
        if (skip("payroll")) return;
        log.info("🌱 Seeding Payroll...");

        svc.saveHero("payroll", ServiceHeroDto.builder()
            .serviceId("payroll")
            .title("Payroll Management")
            .subtitle("Intelligent Payroll Automation")
            .tagline("Accurate. Compliant. Always On Time.")
            .description("Enterprise-grade payroll processing with statutory compliance, multi-currency support, and zero-error guarantees. Automate every payroll cycle with complete confidence.")
            .heroImage("https://images.unsplash.com/photo-1554224155-6726b3ff858f?w=1200&q=80")
            .color("#059669")
            .tag("Finance")
            .build());

        svc.saveOverview("payroll", new ServiceOverviewDto(
            "Our payroll engine processes complex salary structures, statutory deductions, and multi-country compliance automatically. From payslip generation to bank disbursements, every step is automated and audit-ready."));

        svc.saveStats("payroll", List.of(
            ServiceStatDto.builder().value("99.98%").label("Payroll Accuracy").build(),
            ServiceStatDto.builder().value("100%").label("On-Time Disbursement").build(),
            ServiceStatDto.builder().value("$2.4B+").label("Payroll Processed").build(),
            ServiceStatDto.builder().value("40+").label("Countries Supported").build()
        ));

        svc.saveModules("payroll", List.of(
            ServiceModuleDto.builder().icon("💳").title("Salary Processing")
                .desc("Flexible salary structures, variable pay, allowances, deductions, and automated gross-to-net calculation for every employee.").build(),
            ServiceModuleDto.builder().icon("🏛️").title("Statutory Compliance")
                .desc("Automatic calculation and filing of PF, ESI, TDS, PT, and other statutory requirements with regulatory updates.").build(),
            ServiceModuleDto.builder().icon("🌍").title("Multi-Country Payroll")
                .desc("Process payroll across 40+ countries with local tax laws, currency conversion, and regional compliance built in.").build(),
            ServiceModuleDto.builder().icon("📄").title("Payslip & Reports")
                .desc("Digital payslips, Form 16, salary registers, and management payroll reports generated instantly.").build(),
            ServiceModuleDto.builder().icon("🏦").title("Bank Integration")
                .desc("Direct bank transfers, NEFT/RTGS processing, and automatic salary disbursement to employee accounts.").build(),
            ServiceModuleDto.builder().icon("🔍").title("Audit & Reconciliation")
                .desc("Complete payroll audit trails, variance reports, and reconciliation dashboards for finance and compliance teams.").build()
        ));

        svc.saveBenefits("payroll", List.of(
            ServiceBenefitDto.builder().title("Zero-Error Payroll Guarantee")
                .desc("Our multi-layer validation engine catches errors before they reach employees. 99.98% accuracy across millions of payroll transactions processed monthly.")
                .image("https://images.unsplash.com/photo-1554224155-6726b3ff858f?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("Statutory Compliance on Autopilot")
                .desc("Never miss a compliance deadline again. Our engine automatically updates with regulatory changes and files returns on time, every time.")
                .image("https://images.unsplash.com/photo-1450101499163-c8848c66ca85?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("Global Payroll Consolidation")
                .desc("Manage payroll across multiple countries, currencies, and entities from a single dashboard. Consolidated reporting with local compliance everywhere.")
                .image("https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=800&q=80").build()
        ));

        svc.saveFeatures("payroll", List.of(
            ServiceFeatureDto.builder().feature("Flexible salary structure builder").build(),
            ServiceFeatureDto.builder().feature("Automated statutory calculations").build(),
            ServiceFeatureDto.builder().feature("Multi-country payroll support").build(),
            ServiceFeatureDto.builder().feature("Direct bank integration").build(),
            ServiceFeatureDto.builder().feature("Digital payslip delivery").build(),
            ServiceFeatureDto.builder().feature("Form 16 & tax filing").build(),
            ServiceFeatureDto.builder().feature("Arrear & bonus processing").build(),
            ServiceFeatureDto.builder().feature("Payroll audit dashboard").build(),
            ServiceFeatureDto.builder().feature("Employee loan management").build(),
            ServiceFeatureDto.builder().feature("Reimbursement processing").build(),
            ServiceFeatureDto.builder().feature("Payroll variance reports").build(),
            ServiceFeatureDto.builder().feature("Year-end compliance pack").build()
        ));

        svc.saveTechSpecs("payroll", List.of(
            ServiceTechSpecDto.builder().label("Processing Speed").value("10,000 employees/min").build(),
            ServiceTechSpecDto.builder().label("Uptime SLA").value("99.99%").build(),
            ServiceTechSpecDto.builder().label("Countries").value("40+").build(),
            ServiceTechSpecDto.builder().label("Compliance").value("SOC 2, ISO 27001").build(),
            ServiceTechSpecDto.builder().label("Bank Integrations").value("500+").build(),
            ServiceTechSpecDto.builder().label("Implementation").value("7–14 Days").build()
        ));

        svc.saveCta("payroll", ServiceCtaDto.builder()
            .badgeText("Ready to Transform?")
            .heading("Deploy Payroll Management In 14 Days.")
            .subText("Join hundreds of enterprises running Payroll on our platform. Get a personalised demo built around your specific workflows and requirements.")
            .buttonLabel("Book Free Demo")
            .trustPoints(List.of("✓ No setup fees", "✓ 14-day go-live", "✓ Dedicated support", "✓ SOC 2 certified"))
            .roiLabel("ROI Timeline").roiValue("3 Months")
            .goLiveLabel("Go-Live").goLiveValue("14 Days").goLiveSubtext("Guided setup")
            .build());

        svc.saveRelated("payroll", List.of(
            ServiceRelatedDto.builder().relatedServiceId("hrms").build(),
            ServiceRelatedDto.builder().relatedServiceId("attendance").build(),
            ServiceRelatedDto.builder().relatedServiceId("accounting").build()
        ));
    }

    // ════════════════════════════════════════════════════════════════
    // CRM
    // ════════════════════════════════════════════════════════════════
    private void seedCrm() {
        if (skip("crm")) return;
        log.info("🌱 Seeding CRM...");

        svc.saveHero("crm", ServiceHeroDto.builder()
            .serviceId("crm")
            .title("CRM Software")
            .subtitle("Customer Relationship Management")
            .tagline("Close More Deals. Build Lasting Customer Relationships.")
            .description("Enterprise CRM that unifies sales pipelines, customer data, and revenue forecasting into one intelligent platform — empowering your sales teams to perform at their best.")
            .heroImage("https://images.unsplash.com/photo-1552664730-d307ca884978?w=1200&q=80")
            .color("#dc2626")
            .tag("Sales")
            .build());

        svc.saveOverview("crm", new ServiceOverviewDto(
            "Our CRM platform gives enterprise sales teams a complete view of every customer relationship, opportunity, and revenue stream. AI-powered insights help your team prioritize the right deals at the right time."));

        svc.saveStats("crm", List.of(
            ServiceStatDto.builder().value("35%").label("Increase in Sales Revenue").build(),
            ServiceStatDto.builder().value("2x").label("Faster Deal Closure").build(),
            ServiceStatDto.builder().value("98%").label("Customer Retention Rate").build(),
            ServiceStatDto.builder().value("500+").label("Sales Teams Powered").build()
        ));

        svc.saveModules("crm", List.of(
            ServiceModuleDto.builder().icon("🎯").title("Pipeline Management")
                .desc("Visual sales pipelines, deal stage tracking, probability scoring, and automated follow-up sequences for every opportunity.").build(),
            ServiceModuleDto.builder().icon("👤").title("Contact & Account Management")
                .desc("360-degree customer profiles, interaction history, document management, and relationship mapping across accounts.").build(),
            ServiceModuleDto.builder().icon("📈").title("Revenue Forecasting")
                .desc("AI-powered revenue forecasting, quota management, and territory planning with real-time pipeline analytics.").build(),
            ServiceModuleDto.builder().icon("📧").title("Sales Automation")
                .desc("Email sequences, task automation, meeting scheduling, and follow-up reminders to keep deals moving forward.").build(),
            ServiceModuleDto.builder().icon("📊").title("Sales Analytics")
                .desc("Win/loss analysis, rep performance dashboards, conversion metrics, and competitive intelligence reporting.").build(),
            ServiceModuleDto.builder().icon("🔗").title("ERP Integration")
                .desc("Native integration with ERP for seamless order management, invoicing, and customer financial history.").build()
        ));

        svc.saveBenefits("crm", List.of(
            ServiceBenefitDto.builder().title("Complete Customer Intelligence")
                .desc("Every interaction, transaction, and touchpoint captured in one place. Your sales team always knows exactly where every deal stands.")
                .image("https://images.unsplash.com/photo-1552664730-d307ca884978?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("AI-Powered Sales Forecasting")
                .desc("Machine learning models analyze your pipeline to predict deal outcomes with 92% accuracy — giving leadership reliable revenue projections.")
                .image("https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("Seamless ERP-CRM Bridge")
                .desc("The moment a deal is won, it flows directly into ERP for order processing, invoicing, and delivery — eliminating manual handoffs.")
                .image("https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=800&q=80").build()
        ));

        svc.saveFeatures("crm", List.of(
            ServiceFeatureDto.builder().feature("Visual pipeline management").build(),
            ServiceFeatureDto.builder().feature("AI lead scoring").build(),
            ServiceFeatureDto.builder().feature("Email & calendar integration").build(),
            ServiceFeatureDto.builder().feature("Quote & proposal builder").build(),
            ServiceFeatureDto.builder().feature("Customer 360° view").build(),
            ServiceFeatureDto.builder().feature("Territory management").build(),
            ServiceFeatureDto.builder().feature("Sales forecasting").build(),
            ServiceFeatureDto.builder().feature("Mobile CRM app").build(),
            ServiceFeatureDto.builder().feature("Workflow automation").build(),
            ServiceFeatureDto.builder().feature("Custom fields & layouts").build(),
            ServiceFeatureDto.builder().feature("ERP order integration").build(),
            ServiceFeatureDto.builder().feature("Advanced analytics").build()
        ));

        svc.saveTechSpecs("crm", List.of(
            ServiceTechSpecDto.builder().label("Deployment").value("Cloud SaaS").build(),
            ServiceTechSpecDto.builder().label("Uptime SLA").value("99.9%").build(),
            ServiceTechSpecDto.builder().label("Data Encryption").value("AES-256").build(),
            ServiceTechSpecDto.builder().label("Compliance").value("GDPR, SOC 2").build(),
            ServiceTechSpecDto.builder().label("Integrations").value("150+ Pre-built").build(),
            ServiceTechSpecDto.builder().label("Implementation").value("7–14 Days").build()
        ));

        svc.saveCta("crm", ServiceCtaDto.builder()
            .badgeText("Ready to Transform?")
            .heading("Deploy CRM Software In 14 Days.")
            .subText("Join hundreds of enterprises running CRM on our platform. Get a personalised demo built around your specific workflows and requirements.")
            .buttonLabel("Book Free Demo")
            .trustPoints(List.of("✓ No setup fees", "✓ 14-day go-live", "✓ Dedicated support", "✓ SOC 2 certified"))
            .roiLabel("ROI Timeline").roiValue("3 Months")
            .goLiveLabel("Go-Live").goLiveValue("14 Days").goLiveSubtext("Guided setup")
            .build());

        svc.saveRelated("crm", List.of(
            ServiceRelatedDto.builder().relatedServiceId("erp").build(),
            ServiceRelatedDto.builder().relatedServiceId("analytics").build(),
            ServiceRelatedDto.builder().relatedServiceId("automation").build()
        ));
    }

    // ════════════════════════════════════════════════════════════════
    // INVENTORY
    // ════════════════════════════════════════════════════════════════
    private void seedInventory() {
        if (skip("inventory")) return;
        log.info("🌱 Seeding Inventory...");

        svc.saveHero("inventory", ServiceHeroDto.builder()
            .serviceId("inventory")
            .title("Inventory Management")
            .subtitle("Intelligent Stock & Warehouse Control")
            .tagline("Total Inventory Visibility. Zero Stockouts. Maximum Efficiency.")
            .description("Real-time inventory tracking, warehouse management, and supply chain coordination — all in one enterprise platform designed to eliminate waste and optimize stock levels.")
            .heroImage("https://images.unsplash.com/photo-1553413077-190dd305871c?w=1200&q=80")
            .color("#d97706")
            .tag("Operations")
            .build());

        svc.saveOverview("inventory", new ServiceOverviewDto(
            "Our inventory management platform gives enterprises complete real-time visibility across every warehouse, location, and product line. AI-powered demand forecasting ensures optimal stock levels and eliminates costly stockouts."));

        svc.saveStats("inventory", List.of(
            ServiceStatDto.builder().value("45%").label("Reduction in Carrying Costs").build(),
            ServiceStatDto.builder().value("99.5%").label("Inventory Accuracy").build(),
            ServiceStatDto.builder().value("0").label("Stockouts with Smart Reorder").build(),
            ServiceStatDto.builder().value("200+").label("Warehouses Managed").build()
        ));

        svc.saveModules("inventory", List.of(
            ServiceModuleDto.builder().icon("📦").title("Stock Management")
                .desc("Real-time stock tracking by SKU, batch, serial number, and expiry. Multi-location inventory with automatic stock valuation.").build(),
            ServiceModuleDto.builder().icon("🏗️").title("Warehouse Management")
                .desc("Bin location management, pick/pack/ship workflows, barcode scanning, and warehouse efficiency analytics.").build(),
            ServiceModuleDto.builder().icon("🔮").title("Demand Forecasting")
                .desc("AI-powered demand prediction, seasonal trend analysis, and automatic reorder point calculations.").build(),
            ServiceModuleDto.builder().icon("🚛").title("Purchase Orders")
                .desc("Automated purchase order generation, supplier comparison, GRN processing, and three-way invoice matching.").build(),
            ServiceModuleDto.builder().icon("🏷️").title("Product Catalogue")
                .desc("Comprehensive product database with variants, pricing tiers, supplier details, and specification sheets.").build(),
            ServiceModuleDto.builder().icon("📊").title("Inventory Analytics")
                .desc("Stock aging, slow-moving analysis, ABC classification, and profitability reporting by product and category.").build()
        ));

        svc.saveBenefits("inventory", List.of(
            ServiceBenefitDto.builder().title("Real-Time Stock Visibility")
                .desc("Know exactly what you have, where it is, and what it's worth — across every location, warehouse, and transit point — in real time.")
                .image("https://images.unsplash.com/photo-1553413077-190dd305871c?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("AI Demand Forecasting")
                .desc("Never overstock or run out again. Our AI analyzes historical patterns, seasonality, and market signals to predict demand with precision.")
                .image("https://images.unsplash.com/photo-1518186285589-2f7649de83e0?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("Seamless Supply Chain")
                .desc("Connect inventory directly to procurement, sales orders, and finance. Automated reordering triggers purchase orders the moment stock hits threshold.")
                .image("https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=800&q=80").build()
        ));

        svc.saveFeatures("inventory", List.of(
            ServiceFeatureDto.builder().feature("Real-time stock tracking").build(),
            ServiceFeatureDto.builder().feature("Multi-warehouse management").build(),
            ServiceFeatureDto.builder().feature("Barcode & QR code scanning").build(),
            ServiceFeatureDto.builder().feature("Automated reorder alerts").build(),
            ServiceFeatureDto.builder().feature("Batch & serial tracking").build(),
            ServiceFeatureDto.builder().feature("Expiry date management").build(),
            ServiceFeatureDto.builder().feature("Supplier management").build(),
            ServiceFeatureDto.builder().feature("GRN & quality inspection").build(),
            ServiceFeatureDto.builder().feature("Stock adjustment & transfers").build(),
            ServiceFeatureDto.builder().feature("Inventory valuation (FIFO/LIFO)").build(),
            ServiceFeatureDto.builder().feature("Mobile warehouse app").build(),
            ServiceFeatureDto.builder().feature("Inventory audit tools").build()
        ));

        svc.saveTechSpecs("inventory", List.of(
            ServiceTechSpecDto.builder().label("Deployment").value("Cloud & On-Premise").build(),
            ServiceTechSpecDto.builder().label("Uptime SLA").value("99.9%").build(),
            ServiceTechSpecDto.builder().label("Scanner Support").value("All Major Brands").build(),
            ServiceTechSpecDto.builder().label("Locations").value("Unlimited").build(),
            ServiceTechSpecDto.builder().label("ERP Integration").value("Native").build(),
            ServiceTechSpecDto.builder().label("Implementation").value("14–21 Days").build()
        ));

        svc.saveCta("inventory", ServiceCtaDto.builder()
            .badgeText("Ready to Transform?")
            .heading("Deploy Inventory Management In 14 Days.")
            .subText("Join hundreds of enterprises running Inventory Management on our platform.")
            .buttonLabel("Book Free Demo")
            .trustPoints(List.of("✓ No setup fees", "✓ 14-day go-live", "✓ Dedicated support", "✓ SOC 2 certified"))
            .roiLabel("ROI Timeline").roiValue("3 Months")
            .goLiveLabel("Go-Live").goLiveValue("14 Days").goLiveSubtext("Guided setup")
            .build());

        svc.saveRelated("inventory", List.of(
            ServiceRelatedDto.builder().relatedServiceId("erp").build(),
            ServiceRelatedDto.builder().relatedServiceId("accounting").build(),
            ServiceRelatedDto.builder().relatedServiceId("automation").build()
        ));
    }

    // ════════════════════════════════════════════════════════════════
    // ACCOUNTING
    // ════════════════════════════════════════════════════════════════
    private void seedAccounting() {
        if (skip("accounting")) return;
        log.info("🌱 Seeding Accounting...");

        svc.saveHero("accounting", ServiceHeroDto.builder()
            .serviceId("accounting")
            .title("Accounting Software")
            .subtitle("Enterprise Financial Management")
            .tagline("Financial Clarity. Compliance Confidence. Audit Ready.")
            .description("Comprehensive accounting software built for enterprise complexity — multi-entity consolidation, statutory compliance, and real-time financial intelligence for finance teams.")
            .heroImage("https://images.unsplash.com/photo-1554224154-26032ffc0d07?w=1200&q=80")
            .color("#0891b2")
            .tag("Finance")
            .build());

        svc.saveOverview("accounting", new ServiceOverviewDto(
            "Our accounting platform handles the financial complexity of modern enterprises. From real-time general ledger to automated GST filing and multi-entity consolidation, your finance team gains speed, accuracy, and confidence."));

        svc.saveStats("accounting", List.of(
            ServiceStatDto.builder().value("80%").label("Faster Month-End Close").build(),
            ServiceStatDto.builder().value("100%").label("GST Compliance").build(),
            ServiceStatDto.builder().value("0").label("Manual Reconciliation Errors").build(),
            ServiceStatDto.builder().value("15+").label("Currencies Supported").build()
        ));

        svc.saveModules("accounting", List.of(
            ServiceModuleDto.builder().icon("📒").title("General Ledger")
                .desc("Real-time GL with multi-dimensional chart of accounts, journal management, and automatic period-end closing routines.").build(),
            ServiceModuleDto.builder().icon("📤").title("Accounts Payable")
                .desc("Invoice processing, three-way matching, payment scheduling, vendor reconciliation, and early payment discount management.").build(),
            ServiceModuleDto.builder().icon("📥").title("Accounts Receivable")
                .desc("Customer invoicing, payment tracking, collections management, aging analysis, and automated payment reminders.").build(),
            ServiceModuleDto.builder().icon("🏛️").title("Tax & GST Compliance")
                .desc("Automated GST calculation, return filing (GSTR-1, 2A, 3B), TDS management, and direct government portal integration.").build(),
            ServiceModuleDto.builder().icon("🔄").title("Bank Reconciliation")
                .desc("Automated bank statement import, intelligent matching, and one-click reconciliation across all bank accounts.").build(),
            ServiceModuleDto.builder().icon("🏢").title("Multi-Entity Consolidation")
                .desc("Consolidate financials across subsidiaries, handle intercompany eliminations, and produce group-level financial statements.").build()
        ));

        svc.saveBenefits("accounting", List.of(
            ServiceBenefitDto.builder().title("Real-Time Financial Intelligence")
                .desc("Access live P&L, balance sheets, and cash flow statements at any time. Your CFO always has the numbers they need — no waiting for month-end.")
                .image("https://images.unsplash.com/photo-1554224154-26032ffc0d07?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("GST & Tax on Autopilot")
                .desc("Automated GST calculation, invoice matching, and direct GSTN portal filing. Stay 100% compliant without manual effort.")
                .image("https://images.unsplash.com/photo-1450101499163-c8848c66ca85?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("Audit-Ready at All Times")
                .desc("Complete transaction trails, document attachments, and approval records make every audit straightforward and stress-free.")
                .image("https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=800&q=80").build()
        ));

        svc.saveFeatures("accounting", List.of(
            ServiceFeatureDto.builder().feature("Real-time general ledger").build(),
            ServiceFeatureDto.builder().feature("Automated bank reconciliation").build(),
            ServiceFeatureDto.builder().feature("GST & TDS compliance").build(),
            ServiceFeatureDto.builder().feature("Multi-entity consolidation").build(),
            ServiceFeatureDto.builder().feature("Budget vs actual tracking").build(),
            ServiceFeatureDto.builder().feature("Financial statement generation").build(),
            ServiceFeatureDto.builder().feature("Fixed asset management").build(),
            ServiceFeatureDto.builder().feature("Cost centre accounting").build(),
            ServiceFeatureDto.builder().feature("Intercompany transactions").build(),
            ServiceFeatureDto.builder().feature("Audit trail & controls").build(),
            ServiceFeatureDto.builder().feature("Custom financial reports").build(),
            ServiceFeatureDto.builder().feature("ERP native integration").build()
        ));

        svc.saveTechSpecs("accounting", List.of(
            ServiceTechSpecDto.builder().label("Deployment").value("Cloud SaaS").build(),
            ServiceTechSpecDto.builder().label("Uptime SLA").value("99.99%").build(),
            ServiceTechSpecDto.builder().label("Currencies").value("15+").build(),
            ServiceTechSpecDto.builder().label("Compliance").value("IFRS, GAAP, GST").build(),
            ServiceTechSpecDto.builder().label("Bank Integrations").value("500+").build(),
            ServiceTechSpecDto.builder().label("Implementation").value("14–30 Days").build()
        ));

        svc.saveCta("accounting", ServiceCtaDto.builder()
            .badgeText("Ready to Transform?")
            .heading("Deploy Accounting Software In 14 Days.")
            .subText("Join hundreds of enterprises running Accounting on our platform.")
            .buttonLabel("Book Free Demo")
            .trustPoints(List.of("✓ No setup fees", "✓ 14-day go-live", "✓ Dedicated support", "✓ SOC 2 certified"))
            .roiLabel("ROI Timeline").roiValue("3 Months")
            .goLiveLabel("Go-Live").goLiveValue("14 Days").goLiveSubtext("Guided setup")
            .build());

        svc.saveRelated("accounting", List.of(
            ServiceRelatedDto.builder().relatedServiceId("erp").build(),
            ServiceRelatedDto.builder().relatedServiceId("payroll").build(),
            ServiceRelatedDto.builder().relatedServiceId("inventory").build()
        ));
    }

    // ════════════════════════════════════════════════════════════════
    // ATTENDANCE
    // ════════════════════════════════════════════════════════════════
    private void seedAttendance() {
        if (skip("attendance")) return;
        log.info("🌱 Seeding Attendance...");

        svc.saveHero("attendance", ServiceHeroDto.builder()
            .serviceId("attendance")
            .title("Attendance Management")
            .subtitle("Smart Workforce Time Tracking")
            .tagline("Accurate Attendance. Smarter Scheduling. Full Compliance.")
            .description("Biometric integration, geo-fencing, shift scheduling, and automated compliance — a complete attendance management solution for enterprise workforces of any size.")
            .heroImage("https://images.unsplash.com/photo-1600880292203-757bb62b4baf?w=1200&q=80")
            .color("#7c3aed")
            .tag("HR Suite")
            .build());

        svc.saveOverview("attendance", new ServiceOverviewDto(
            "Our attendance management system integrates with biometric devices, mobile apps, and geo-fencing to capture accurate time data across every location. Automated shift management and payroll integration eliminate manual effort."));

        svc.saveStats("attendance", List.of(
            ServiceStatDto.builder().value("100%").label("Attendance Accuracy").build(),
            ServiceStatDto.builder().value("70%").label("Reduction in Payroll Errors").build(),
            ServiceStatDto.builder().value("50+").label("Biometric Devices Supported").build(),
            ServiceStatDto.builder().value("Real-time").label("Attendance Visibility").build()
        ));

        svc.saveModules("attendance", List.of(
            ServiceModuleDto.builder().icon("👆").title("Biometric Integration")
                .desc("Support for 50+ biometric device brands including fingerprint, face recognition, and card-based attendance systems.").build(),
            ServiceModuleDto.builder().icon("📍").title("Geo-Fencing")
                .desc("Location-based attendance marking, remote employee tracking, and field staff check-in verification via mobile.").build(),
            ServiceModuleDto.builder().icon("🔄").title("Shift Management")
                .desc("Flexible shift scheduling, rotational shifts, night allowances, overtime calculation, and shift swap workflows.").build(),
            ServiceModuleDto.builder().icon("📊").title("Attendance Analytics")
                .desc("Absenteeism trends, punctuality reports, overtime analysis, and department-level attendance dashboards.").build(),
            ServiceModuleDto.builder().icon("💰").title("Payroll Integration")
                .desc("Automatic attendance data flow to payroll for accurate salary calculations including overtime and allowances.").build(),
            ServiceModuleDto.builder().icon("📱").title("Mobile Attendance")
                .desc("Employee self-service mobile app for clock-in/out, leave requests, and real-time attendance visibility.").build()
        ));

        svc.saveBenefits("attendance", List.of(
            ServiceBenefitDto.builder().title("Biometric Precision")
                .desc("Eliminate buddy punching and proxy attendance with multi-modal biometric verification across all your sites and locations.")
                .image("https://images.unsplash.com/photo-1600880292203-757bb62b4baf?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("Automated Payroll Sync")
                .desc("Attendance data flows directly into payroll calculation — eliminating manual data entry, reducing errors by 70%, and accelerating payroll processing.")
                .image("https://images.unsplash.com/photo-1554224155-6726b3ff858f?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("Remote Workforce Control")
                .desc("Manage attendance for office, field, and remote employees through geo-fencing, mobile check-ins, and live location tracking.")
                .image("https://images.unsplash.com/photo-1522202176988-66273c2fd55f?w=800&q=80").build()
        ));

        svc.saveFeatures("attendance", List.of(
            ServiceFeatureDto.builder().feature("50+ biometric device support").build(),
            ServiceFeatureDto.builder().feature("Geo-fencing & GPS tracking").build(),
            ServiceFeatureDto.builder().feature("Flexible shift scheduling").build(),
            ServiceFeatureDto.builder().feature("Overtime & holiday management").build(),
            ServiceFeatureDto.builder().feature("Automated payroll sync").build(),
            ServiceFeatureDto.builder().feature("Leave integration").build(),
            ServiceFeatureDto.builder().feature("Real-time dashboards").build(),
            ServiceFeatureDto.builder().feature("Absenteeism alerts").build(),
            ServiceFeatureDto.builder().feature("Mobile clock-in/out").build(),
            ServiceFeatureDto.builder().feature("Roster management").build(),
            ServiceFeatureDto.builder().feature("Compliance reporting").build(),
            ServiceFeatureDto.builder().feature("Multi-site management").build()
        ));

        svc.saveTechSpecs("attendance", List.of(
            ServiceTechSpecDto.builder().label("Deployment").value("Cloud & On-Premise").build(),
            ServiceTechSpecDto.builder().label("Biometric Support").value("50+ Devices").build(),
            ServiceTechSpecDto.builder().label("Mobile OS").value("iOS & Android").build(),
            ServiceTechSpecDto.builder().label("Data Sync").value("Real-time").build(),
            ServiceTechSpecDto.builder().label("Payroll Integration").value("Native").build(),
            ServiceTechSpecDto.builder().label("Implementation").value("7–14 Days").build()
        ));

        svc.saveCta("attendance", ServiceCtaDto.builder()
            .badgeText("Ready to Transform?")
            .heading("Deploy Attendance Management In 14 Days.")
            .subText("Join hundreds of enterprises running Attendance Management on our platform.")
            .buttonLabel("Book Free Demo")
            .trustPoints(List.of("✓ No setup fees", "✓ 14-day go-live", "✓ Dedicated support", "✓ SOC 2 certified"))
            .roiLabel("ROI Timeline").roiValue("3 Months")
            .goLiveLabel("Go-Live").goLiveValue("14 Days").goLiveSubtext("Guided setup")
            .build());

        svc.saveRelated("attendance", List.of(
            ServiceRelatedDto.builder().relatedServiceId("hrms").build(),
            ServiceRelatedDto.builder().relatedServiceId("payroll").build(),
            ServiceRelatedDto.builder().relatedServiceId("mobile").build()
        ));
    }

    // ════════════════════════════════════════════════════════════════
    // AUTOMATION
    // ════════════════════════════════════════════════════════════════
    private void seedAutomation() {
        if (skip("automation")) return;
        log.info("🌱 Seeding Automation...");

        svc.saveHero("automation", ServiceHeroDto.builder()
            .serviceId("automation")
            .title("Business Automation")
            .subtitle("Intelligent Process Orchestration")
            .tagline("Automate. Orchestrate. Accelerate.")
            .description("Replace manual workflows with intelligent automation across every department. Build, deploy, and manage enterprise-grade process automations without writing a single line of code.")
            .heroImage("https://images.unsplash.com/photo-1518186285589-2f7649de83e0?w=1200&q=80")
            .color("#1a56e8")
            .tag("Platform")
            .build());

        svc.saveOverview("automation", new ServiceOverviewDto(
            "Our business automation platform enables enterprises to digitize and automate complex workflows across every department — from approvals and document management to cross-system orchestration and AI-triggered events."));

        svc.saveStats("automation", List.of(
            ServiceStatDto.builder().value("80%").label("Reduction in Manual Tasks").build(),
            ServiceStatDto.builder().value("5x").label("Process Speed Improvement").build(),
            ServiceStatDto.builder().value("1000+").label("Workflows Automated").build(),
            ServiceStatDto.builder().value("60%").label("Cost Savings on Operations").build()
        ));

        svc.saveModules("automation", List.of(
            ServiceModuleDto.builder().icon("🔄").title("Workflow Builder")
                .desc("Drag-and-drop workflow designer with conditional logic, parallel processing, and cross-module automation capabilities.").build(),
            ServiceModuleDto.builder().icon("✅").title("Approval Management")
                .desc("Multi-level approval chains, delegation rules, escalation policies, and mobile approval for on-the-go executives.").build(),
            ServiceModuleDto.builder().icon("📄").title("Document Automation")
                .desc("Template-based document generation, e-signature integration, version control, and automated distribution workflows.").build(),
            ServiceModuleDto.builder().icon("🤖").title("AI Trigger Engine")
                .desc("Event-based automation triggers, anomaly detection responses, and AI-powered process recommendations.").build(),
            ServiceModuleDto.builder().icon("🔗").title("System Integration")
                .desc("Connect with 200+ business applications through pre-built connectors, REST APIs, and webhook-based integrations.").build(),
            ServiceModuleDto.builder().icon("📊").title("Process Analytics")
                .desc("Workflow performance metrics, bottleneck identification, SLA tracking, and continuous improvement insights.").build()
        ));

        svc.saveBenefits("automation", List.of(
            ServiceBenefitDto.builder().title("No-Code Workflow Design")
                .desc("Business users build and modify complex workflows through an intuitive drag-and-drop interface — no IT dependency required.")
                .image("https://images.unsplash.com/photo-1518186285589-2f7649de83e0?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("Cross-Platform Orchestration")
                .desc("Automate processes that span multiple systems — ERP, HRMS, CRM, and third-party tools — all coordinated from a single automation hub.")
                .image("https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("Measurable Process ROI")
                .desc("Track every automation's performance, time savings, and cost impact. Prove the value of digitisation with real, measurable data.")
                .image("https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=800&q=80").build()
        ));

        svc.saveFeatures("automation", List.of(
            ServiceFeatureDto.builder().feature("Drag-and-drop workflow builder").build(),
            ServiceFeatureDto.builder().feature("Multi-level approval chains").build(),
            ServiceFeatureDto.builder().feature("Document generation & e-sign").build(),
            ServiceFeatureDto.builder().feature("Scheduled task automation").build(),
            ServiceFeatureDto.builder().feature("API & webhook integration").build(),
            ServiceFeatureDto.builder().feature("Conditional logic & branching").build(),
            ServiceFeatureDto.builder().feature("Role-based routing").build(),
            ServiceFeatureDto.builder().feature("Mobile approval app").build(),
            ServiceFeatureDto.builder().feature("Process performance analytics").build(),
            ServiceFeatureDto.builder().feature("SLA monitoring & alerts").build(),
            ServiceFeatureDto.builder().feature("Audit trail & compliance").build(),
            ServiceFeatureDto.builder().feature("200+ pre-built connectors").build()
        ));

        svc.saveTechSpecs("automation", List.of(
            ServiceTechSpecDto.builder().label("Deployment").value("Cloud SaaS").build(),
            ServiceTechSpecDto.builder().label("Uptime SLA").value("99.9%").build(),
            ServiceTechSpecDto.builder().label("Connectors").value("200+ Pre-built").build(),
            ServiceTechSpecDto.builder().label("Workflow Limit").value("Unlimited").build(),
            ServiceTechSpecDto.builder().label("Code Required").value("Zero").build(),
            ServiceTechSpecDto.builder().label("Implementation").value("7 Days").build()
        ));

        svc.saveCta("automation", ServiceCtaDto.builder()
            .badgeText("Ready to Transform?")
            .heading("Deploy Business Automation In 14 Days.")
            .subText("Join hundreds of enterprises running Business Automation on our platform.")
            .buttonLabel("Book Free Demo")
            .trustPoints(List.of("✓ No setup fees", "✓ 14-day go-live", "✓ Dedicated support", "✓ SOC 2 certified"))
            .roiLabel("ROI Timeline").roiValue("3 Months")
            .goLiveLabel("Go-Live").goLiveValue("14 Days").goLiveSubtext("Guided setup")
            .build());

        svc.saveRelated("automation", List.of(
            ServiceRelatedDto.builder().relatedServiceId("erp").build(),
            ServiceRelatedDto.builder().relatedServiceId("crm").build(),
            ServiceRelatedDto.builder().relatedServiceId("analytics").build()
        ));
    }

    // ════════════════════════════════════════════════════════════════
    // ANALYTICS
    // ════════════════════════════════════════════════════════════════
    private void seedAnalytics() {
        if (skip("analytics")) return;
        log.info("🌱 Seeding Analytics...");

        svc.saveHero("analytics", ServiceHeroDto.builder()
            .serviceId("analytics")
            .title("AI Analytics")
            .subtitle("Enterprise Business Intelligence")
            .tagline("Predict. Analyze. Decide. With Confidence.")
            .description("AI-powered business intelligence that transforms raw enterprise data into predictive insights, strategic recommendations, and real-time intelligence for every level of your organization.")
            .heroImage("https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=1200&q=80")
            .color("#1a56e8")
            .tag("Intelligence")
            .build());

        svc.saveOverview("analytics", new ServiceOverviewDto(
            "Our AI Analytics platform ingests data from every module and external source, applying machine learning models to surface insights, predict outcomes, and recommend actions — turning your enterprise data into competitive advantage."));

        svc.saveStats("analytics", List.of(
            ServiceStatDto.builder().value("4x").label("Faster Decision Making").build(),
            ServiceStatDto.builder().value("92%").label("Forecast Accuracy").build(),
            ServiceStatDto.builder().value("Real-time").label("Data Processing").build(),
            ServiceStatDto.builder().value("100+").label("Pre-built Dashboards").build()
        ));

        svc.saveModules("analytics", List.of(
            ServiceModuleDto.builder().icon("🧠").title("Predictive Intelligence")
                .desc("Machine learning models that predict revenue, attrition, demand, cash flow, and operational risks before they materialize.").build(),
            ServiceModuleDto.builder().icon("📊").title("Executive Dashboards")
                .desc("Board-level and C-suite dashboards with live KPIs, trend analysis, and drill-down capabilities across all business units.").build(),
            ServiceModuleDto.builder().icon("🔍").title("Anomaly Detection")
                .desc("AI-powered monitoring that automatically identifies unusual patterns, fraud signals, and performance deviations in real time.").build(),
            ServiceModuleDto.builder().icon("📈").title("Custom Report Builder")
                .desc("Drag-and-drop report designer with calculated fields, cross-module data joins, and scheduled report distribution.").build(),
            ServiceModuleDto.builder().icon("🌐").title("Data Integration")
                .desc("Connect and analyze data from ERP, HRMS, CRM, external APIs, and third-party databases in a unified analytics layer.").build(),
            ServiceModuleDto.builder().icon("💬").title("Natural Language Queries")
                .desc("Ask business questions in plain English and receive instant AI-generated charts, summaries, and actionable insights.").build()
        ));

        svc.saveBenefits("analytics", List.of(
            ServiceBenefitDto.builder().title("Predictive Business Intelligence")
                .desc("Move from reactive to proactive management. Our AI identifies trends and risks weeks in advance, giving leadership time to act strategically.")
                .image("https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("Unified Data Layer")
                .desc("Break down data silos by connecting every business system into a single analytics layer. One version of the truth, across your entire enterprise.")
                .image("https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("Self-Service Analytics")
                .desc("Empower every business user to explore data, build reports, and generate insights without relying on IT or data science teams.")
                .image("https://images.unsplash.com/photo-1518186285589-2f7649de83e0?w=800&q=80").build()
        ));

        svc.saveFeatures("analytics", List.of(
            ServiceFeatureDto.builder().feature("AI predictive models").build(),
            ServiceFeatureDto.builder().feature("Real-time executive dashboards").build(),
            ServiceFeatureDto.builder().feature("Anomaly detection & alerts").build(),
            ServiceFeatureDto.builder().feature("Natural language querying").build(),
            ServiceFeatureDto.builder().feature("Custom report builder").build(),
            ServiceFeatureDto.builder().feature("Scheduled report delivery").build(),
            ServiceFeatureDto.builder().feature("Multi-source data connectors").build(),
            ServiceFeatureDto.builder().feature("Drill-down & pivot analysis").build(),
            ServiceFeatureDto.builder().feature("Mobile analytics app").build(),
            ServiceFeatureDto.builder().feature("Embedded analytics").build(),
            ServiceFeatureDto.builder().feature("Data export (Excel, PDF)").build(),
            ServiceFeatureDto.builder().feature("Role-based data access").build()
        ));

        svc.saveTechSpecs("analytics", List.of(
            ServiceTechSpecDto.builder().label("Data Refresh").value("Real-time").build(),
            ServiceTechSpecDto.builder().label("ML Models").value("50+ Pre-trained").build(),
            ServiceTechSpecDto.builder().label("Connectors").value("100+ Sources").build(),
            ServiceTechSpecDto.builder().label("Dashboards").value("100+ Pre-built").build(),
            ServiceTechSpecDto.builder().label("Users").value("Unlimited").build(),
            ServiceTechSpecDto.builder().label("Implementation").value("7–14 Days").build()
        ));

        svc.saveCta("analytics", ServiceCtaDto.builder()
            .badgeText("Ready to Transform?")
            .heading("Deploy AI Analytics In 14 Days.")
            .subText("Join hundreds of enterprises running AI Analytics on our platform.")
            .buttonLabel("Book Free Demo")
            .trustPoints(List.of("✓ No setup fees", "✓ 14-day go-live", "✓ Dedicated support", "✓ SOC 2 certified"))
            .roiLabel("ROI Timeline").roiValue("3 Months")
            .goLiveLabel("Go-Live").goLiveValue("14 Days").goLiveSubtext("Guided setup")
            .build());

        svc.saveRelated("analytics", List.of(
            ServiceRelatedDto.builder().relatedServiceId("erp").build(),
            ServiceRelatedDto.builder().relatedServiceId("crm").build(),
            ServiceRelatedDto.builder().relatedServiceId("automation").build()
        ));
    }

    // ════════════════════════════════════════════════════════════════
    // MOBILE
    // ════════════════════════════════════════════════════════════════
    private void seedMobile() {
        if (skip("mobile")) return;
        log.info("🌱 Seeding Mobile...");

        svc.saveHero("mobile", ServiceHeroDto.builder()
            .serviceId("mobile")
            .title("Mobile App Solutions")
            .subtitle("Enterprise Mobility Platform")
            .tagline("Full Enterprise Power. In Your Pocket.")
            .description("Native iOS and Android applications that give your workforce, managers, and executives complete platform access from any device — with offline capability and enterprise-grade security.")
            .heroImage("https://images.unsplash.com/photo-1512941937669-90a1b58e7e9c?w=1200&q=80")
            .color("#059669")
            .tag("Mobile")
            .build());

        svc.saveOverview("mobile", new ServiceOverviewDto(
            "Our enterprise mobile platform extends every ERP and HRMS capability to mobile devices. Employees manage leaves and payslips, managers approve workflows, and executives review KPIs — all from beautifully designed native apps."));

        svc.saveStats("mobile", List.of(
            ServiceStatDto.builder().value("4.8★").label("App Store Rating").build(),
            ServiceStatDto.builder().value("1200+").label("Daily Active Users").build(),
            ServiceStatDto.builder().value("8400+").label("Daily Actions Completed").build(),
            ServiceStatDto.builder().value("Offline").label("Mode Available").build()
        ));

        svc.saveModules("mobile", List.of(
            ServiceModuleDto.builder().icon("👤").title("Employee Self-Service")
                .desc("Leave requests, payslip downloads, expense claims, attendance marking, and personal data management from mobile.").build(),
            ServiceModuleDto.builder().icon("✅").title("Manager Dashboard")
                .desc("Team attendance, leave approvals, performance reviews, and real-time team analytics optimized for mobile management.").build(),
            ServiceModuleDto.builder().icon("📊").title("Executive Analytics")
                .desc("Executive KPI dashboards, financial summaries, and business performance metrics designed for mobile consumption.").build(),
            ServiceModuleDto.builder().icon("🔔").title("Smart Notifications")
                .desc("Context-aware push notifications for approvals, deadlines, anomalies, and action items requiring immediate attention.").build(),
            ServiceModuleDto.builder().icon("📴").title("Offline Capability")
                .desc("Continue working without internet connectivity. Data syncs automatically when connection is restored.").build(),
            ServiceModuleDto.builder().icon("🔒").title("Mobile Security")
                .desc("Biometric authentication, device management, remote wipe capability, and enterprise-grade data encryption.").build()
        ));

        svc.saveBenefits("mobile", List.of(
            ServiceBenefitDto.builder().title("Productivity Without Boundaries")
                .desc("Your workforce is productive anywhere. Field teams, remote employees, and travelling executives all stay connected and effective.")
                .image("https://images.unsplash.com/photo-1512941937669-90a1b58e7e9c?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("Instant Approval Workflows")
                .desc("Managers approve leaves, expenses, and purchase orders from their phone in seconds. No more approval bottlenecks waiting for desktop access.")
                .image("https://images.unsplash.com/photo-1542744173-8e7e53415bb0?w=800&q=80").build(),
            ServiceBenefitDto.builder().title("Enterprise-Grade Mobile Security")
                .desc("Biometric login, encrypted data storage, and MDM integration ensure your enterprise data is always protected on mobile devices.")
                .image("https://images.unsplash.com/photo-1600880292203-757bb62b4baf?w=800&q=80").build()
        ));

        svc.saveFeatures("mobile", List.of(
            ServiceFeatureDto.builder().feature("Native iOS & Android apps").build(),
            ServiceFeatureDto.builder().feature("Employee self-service portal").build(),
            ServiceFeatureDto.builder().feature("Manager approval workflows").build(),
            ServiceFeatureDto.builder().feature("Executive KPI dashboards").build(),
            ServiceFeatureDto.builder().feature("Biometric login (Face/Touch ID)").build(),
            ServiceFeatureDto.builder().feature("Offline mode with sync").build(),
            ServiceFeatureDto.builder().feature("Push notification engine").build(),
            ServiceFeatureDto.builder().feature("Expense claim submission").build(),
            ServiceFeatureDto.builder().feature("Attendance clock-in/out").build(),
            ServiceFeatureDto.builder().feature("Document viewer & signing").build(),
            ServiceFeatureDto.builder().feature("Team calendar & scheduling").build(),
            ServiceFeatureDto.builder().feature("MDM & device management").build()
        ));

        svc.saveTechSpecs("mobile", List.of(
            ServiceTechSpecDto.builder().label("Platforms").value("iOS 14+ & Android 10+").build(),
            ServiceTechSpecDto.builder().label("Security").value("MDM & Biometric").build(),
            ServiceTechSpecDto.builder().label("Offline Mode").value("Full Capability").build(),
            ServiceTechSpecDto.builder().label("Push Notifications").value("Real-time").build(),
            ServiceTechSpecDto.builder().label("Data Encryption").value("AES-256").build(),
            ServiceTechSpecDto.builder().label("Implementation").value("Day 1 Ready").build()
        ));

        svc.saveCta("mobile", ServiceCtaDto.builder()
            .badgeText("Ready to Transform?")
            .heading("Deploy Mobile App Solutions In 14 Days.")
            .subText("Join hundreds of enterprises running Mobile App Solutions on our platform.")
            .buttonLabel("Book Free Demo")
            .trustPoints(List.of("✓ No setup fees", "✓ 14-day go-live", "✓ Dedicated support", "✓ SOC 2 certified"))
            .roiLabel("ROI Timeline").roiValue("3 Months")
            .goLiveLabel("Go-Live").goLiveValue("14 Days").goLiveSubtext("Guided setup")
            .build());

        svc.saveRelated("mobile", List.of(
            ServiceRelatedDto.builder().relatedServiceId("hrms").build(),
            ServiceRelatedDto.builder().relatedServiceId("attendance").build(),
            ServiceRelatedDto.builder().relatedServiceId("erp").build()
        ));
    }
}