package com.example.ez.intilizer.casestudy;

import com.example.ez.casestudy.dto.ProjectDTO;
import com.example.ez.casestudy.repo.ProjectRepository;
import com.example.ez.casestudy.service.CaseStudyProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CaseStudyDataInitializer implements CommandLineRunner {

    private final CaseStudyProjectService caseStudyProjectService;
    private final ProjectRepository projectRepository;

    @Override
    public void run(String... args) throws Exception {
        if (projectRepository.count() > 0) {
            log.info("Database already has {} projects. Skipping initialization.", projectRepository.count());
            return;
        }
        log.info("Initializing Construction ERP sample data...");
        createAllProjects();
        log.info("Sample data initialization complete. Total projects: {}", projectRepository.count());
    }

    private void createAllProjects() {
        caseStudyProjectService.saveCaseStudy(buildProjectManagementProject());
        caseStudyProjectService.saveCaseStudy(buildFinancialManagementProject());
        caseStudyProjectService.saveCaseStudy(buildProcurementSupplyChainProject());
        caseStudyProjectService.saveCaseStudy(buildHumanResourcesLaborProject());
        caseStudyProjectService.saveCaseStudy(buildEquipmentAssetProject());
        caseStudyProjectService.saveCaseStudy(buildDocumentContractProject());
        caseStudyProjectService.saveCaseStudy(buildSubcontractorVendorProject());
        caseStudyProjectService.saveCaseStudy(buildQualitySafetyProject());
        caseStudyProjectService.saveCaseStudy(buildReportingBIProject());
        caseStudyProjectService.saveCaseStudy(buildCRMClientProject());
    }

    // ==================================================================================
    // PROJECT 1: PROJECT MANAGEMENT
    // ==================================================================================
    private ProjectDTO buildProjectManagementProject() {
        ProjectDTO dto = new ProjectDTO();

        ProjectDTO.MetaDTO meta = new ProjectDTO.MetaDTO();
        meta.setTitle("BuildTrack - Enterprise Project Management System");
        meta.setClient("Skyline Construction Group");
        meta.setCategory("Project Management");
        meta.setYear("2024");
        meta.setDuration("10 Months");
        meta.setRole("Full Stack Lead");
        meta.setWebsite("https://buildtrack.erp");
        dto.setMeta(meta);

        dto.setHeroImage("https://placehold.co/1200x600/1e3a5f/ffffff?text=BuildTrack+Project+Management");
        dto.setGallery(List.of(
                "https://placehold.co/800x500/e8f4f8/1e3a5f?text=Gantt+Chart",
                "https://placehold.co/800x500/e8f4f8/1e3a5f?text=Milestone+Tracker",
                "https://placehold.co/800x500/e8f4f8/1e3a5f?text=Resource+Allocation"
        ));

        ProjectDTO.NarrativeDTO challenge = new ProjectDTO.NarrativeDTO();
        challenge.setDescription("Skyline Construction was managing 47 active projects across 12 states using spreadsheets and disconnected tools. Project delays averaged 23 days, cost overruns hit 18%, and resource conflicts between sites caused idle labor and equipment. There was zero visibility into cross-project dependencies.");
        challenge.setPoints(List.of(
                "47 projects managed via disconnected spreadsheets",
                "Average 23-day delay on project deliveries",
                "18% cost overrun on 60% of projects",
                "Resource conflicts causing idle labor and equipment",
                "No visibility into cross-project dependencies and risks"
        ));
        dto.setChallenge(challenge);

        ProjectDTO.NarrativeDTO solution = new ProjectDTO.NarrativeDTO();
        solution.setDescription("Built a unified project management platform with interactive Gantt charts, critical path analysis, resource leveling algorithms, and real-time milestone tracking. Integrated with BIM models for 4D scheduling and automated delay prediction using historical data.");
        solution.setPoints(List.of(
                "Interactive Gantt charts with drag-and-drop scheduling",
                "Critical path analysis with automated float calculation",
                "AI-powered delay prediction with 89% accuracy",
                "Resource leveling across 47 projects in real-time",
                "4D BIM integration for visual timeline planning"
        ));
        dto.setSolution(solution);

        dto.setResults(List.of(
                buildResult("On-Time Delivery", "94%", "Up from 62%"),
                buildResult("Cost Overrun", "4%", "Down from 18%"),
                buildResult("Resource Utilization", "87%", "Up from 58%"),
                buildResult("Planning Time", "2 Hours", "Down from 3 Days")
        ));

        dto.setTechStack(List.of(
                buildTechStack("Frontend", List.of("React", "TypeScript", "D3.js", "Material UI")),
                buildTechStack("Backend", List.of("Spring Boot 3", "GraphQL", "WebSocket", "Redis")),
                buildTechStack("Infra", List.of("AWS", "PostgreSQL", "Elasticsearch", "Kafka")),
                buildTechStack("Tools", List.of("Docker", "Kubernetes", "Jenkins", "Grafana"))
        ));

        dto.setProcess(List.of(
                buildProcess("Discovery & Audit", "Stakeholder interviews across 12 states, legacy tool audit, workflow mapping for 47 projects", "4"),
                buildProcess("Architecture Design", "Microservices blueprint, real-time sync design, BIM integration API specification", "5"),
                buildProcess("Core Development", "Gantt engine, resource allocator, delay prediction ML model, mobile app", "24"),
                buildProcess("Rollout & Training", "Phased rollout by region, superintendent training, legacy data migration", "8")
        ));

        dto.setFeatures(List.of(
                buildFeature("Dynamic Gantt Scheduling", "Drag-and-drop project timelines with automatic critical path recalculation, dependency chaining, and real-time delay impact analysis across all active projects"),
                buildFeature("AI Delay Prediction", "Machine learning model analyzing 5 years of project data to predict delays 14 days in advance with 89% accuracy and suggest mitigation strategies"),
                buildFeature("Resource Command Center", "Cross-project resource allocation dashboard showing labor, equipment, and material availability with conflict detection and auto-resolution suggestions"),
                buildFeature("4D BIM Timeline", "Integration with Revit/Navisworks to visualize construction sequences in 3D with time-based animation for clash detection and schedule optimization")
        ));

        ProjectDTO.TestimonialDTO testimonial = new ProjectDTO.TestimonialDTO();
        testimonial.setQuote("BuildTrack transformed how we run projects. We went from firefighting delays to preventing them. Our superintendents now have real-time visibility into every dependency across all 47 sites.");
        testimonial.setAuthor("Michael Torres");
        testimonial.setRole("VP of Operations, Skyline Construction Group");
        dto.setTestimonial(testimonial);

        return dto;
    }

    // ==================================================================================
    // PROJECT 2: FINANCIAL MANAGEMENT & ACCOUNTING
    // ==================================================================================
    private ProjectDTO buildFinancialManagementProject() {
        ProjectDTO dto = new ProjectDTO();

        ProjectDTO.MetaDTO meta = new ProjectDTO.MetaDTO();
        meta.setTitle("CostMaster - Construction Financial Control Platform");
        meta.setClient("Pinnacle Builders Inc.");
        meta.setCategory("Financial Management & Accounting");
        meta.setYear("2024");
        meta.setDuration("8 Months");
        meta.setRole("Backend Architect");
        meta.setWebsite("https://costmaster.erp");
        dto.setMeta(meta);

        dto.setHeroImage("https://placehold.co/1200x600/0f5132/ffffff?text=CostMaster+Financial+Control");
        dto.setGallery(List.of(
                "https://placehold.co/800x500/d1fae5/0f5132?text=Budget+Dashboard",
                "https://placehold.co/800x500/d1fae5/0f5132?text=Cost+Breakdown",
                "https://placehold.co/800x500/d1fae5/0f5132?text=Cash+Flow+Forecast"
        ));

        ProjectDTO.NarrativeDTO challenge = new ProjectDTO.NarrativeDTO();
        challenge.setDescription("Pinnacle Builders was bleeding cash across 23 active projects with no unified financial view. Cost codes were inconsistent, change orders were lost in email threads, and cash flow forecasting was a monthly guessing game. They had $4.2M in unbilled work and $1.8M in disputed invoices.");
        challenge.setPoints(List.of(
                "$4.2M in unbilled work sitting in spreadsheets",
                "$1.8M in disputed invoices with no audit trail",
                "Inconsistent cost codes across 23 projects",
                "Change orders lost in email threads",
                "Cash flow forecasting was pure guesswork"
        ));
        dto.setChallenge(challenge);

        ProjectDTO.NarrativeDTO solution = new ProjectDTO.NarrativeDTO();
        solution.setDescription("Built a comprehensive financial management platform with unified cost coding, automated billing workflows, real-time WIP tracking, AI-powered cash flow forecasting, and integrated change order management with full audit trails.");
        solution.setPoints(List.of(
                "Unified cost code system across all projects",
                "Automated billing reducing unbilled work to $180K",
                "AI cash flow forecasting with 94% accuracy",
                "Change order workflow with approval chains",
                "Real-time WIP and earned value reporting"
        ));
        dto.setSolution(solution);

        dto.setResults(List.of(
                buildResult("Unbilled Work", "$180K", "Down from $4.2M"),
                buildResult("Invoice Disputes", "$45K", "Down from $1.8M"),
                buildResult("Forecast Accuracy", "94%", "Up from 62%"),
                buildResult("Billing Cycle", "3 Days", "Down from 21 Days")
        ));

        dto.setTechStack(List.of(
                buildTechStack("Frontend", List.of("React", "TypeScript", "Recharts", "AG Grid")),
                buildTechStack("Backend", List.of("Spring Boot", "Java 21", "GraphQL", "Camunda")),
                buildTechStack("Infra", List.of("AWS", "PostgreSQL", "Redis", "Apache Kafka")),
                buildTechStack("Tools", List.of("Docker", "Kubernetes", "GitHub Actions", "Tableau"))
        ));

        dto.setProcess(List.of(
                buildProcess("Financial Audit", "Cost code standardization, billing workflow mapping, WIP calculation analysis", "3"),
                buildProcess("Platform Architecture", "General ledger design, approval workflow engine, forecasting model specification", "4"),
                buildProcess("Core Development", "Billing automation, change order system, cash flow AI, reporting engine", "20"),
                buildProcess("Integration & Go-Live", "QuickBooks/Sage integration, accountant training, parallel run validation", "6")
        ));

        dto.setFeatures(List.of(
                buildFeature("Automated Billing Engine", "Auto-generates progress invoices from daily field reports, subcontractor billings, and material receipts with zero manual data entry and 3-day billing cycles"),
                buildFeature("AI Cash Flow Forecaster", "LSTM neural network predicting 90-day cash flow with 94% accuracy using historical patterns, committed costs, and payment terms"),
                buildFeature("Change Order Control", "Digital change order workflow with automated impact analysis on budget, schedule, and scope with full approval chain and audit trail"),
                buildFeature("Earned Value Dashboard", "Real-time CPI/SPI metrics, cost variance analysis, and at-completion forecasting for every cost code across all 23 projects")
        ));

        ProjectDTO.TestimonialDTO testimonial = new ProjectDTO.TestimonialDTO();
        testimonial.setQuote("CostMaster gave us financial control we never had before. We recovered $3.8M in unbilled work in the first quarter alone. Our CFO now has real-time visibility into every dollar across all projects.");
        testimonial.setAuthor("Jennifer Walsh");
        testimonial.setRole("CFO, Pinnacle Builders Inc.");
        dto.setTestimonial(testimonial);

        return dto;
    }

    // ==================================================================================
    // PROJECT 3: PROCUREMENT & SUPPLY CHAIN
    // ==================================================================================
    private ProjectDTO buildProcurementSupplyChainProject() {
        ProjectDTO dto = new ProjectDTO();

        ProjectDTO.MetaDTO meta = new ProjectDTO.MetaDTO();
        meta.setTitle("ProcureMax - Smart Construction Procurement Platform");
        meta.setClient("IronFrame Structures LLC");
        meta.setCategory("Procurement & Supply Chain");
        meta.setYear("2024");
        meta.setDuration("9 Months");
        meta.setRole("Full Stack Developer");
        meta.setWebsite("https://procuremax.erp");
        dto.setMeta(meta);

        dto.setHeroImage("https://placehold.co/1200x600/7c2d12/ffffff?text=ProcureMax+Procurement");
        dto.setGallery(List.of(
                "https://placehold.co/800x500/ffedd5/7c2d12?text=Vendor+Portal",
                "https://placehold.co/800x500/ffedd5/7c2d12?text=Purchase+Orders",
                "https://placehold.co/800x500/ffedd5/7c2d12?text=Inventory+Tracker"
        ));

        ProjectDTO.NarrativeDTO challenge = new ProjectDTO.NarrativeDTO();
        challenge.setDescription("IronFrame was managing procurement for 15 steel-frame projects with 200+ vendors via phone calls and email. Material delays caused 34% of project overruns, inventory was either overstocked or missing, and purchase orders took 5 days to process. They had no visibility into vendor performance.");
        challenge.setPoints(List.of(
                "200+ vendors managed via phone and email",
                "Material delays causing 34% of project overruns",
                "5-day average purchase order processing time",
                "Inventory either overstocked ($2.1M) or missing",
                "Zero vendor performance tracking or benchmarking"
        ));
        dto.setChallenge(challenge);

        ProjectDTO.NarrativeDTO solution = new ProjectDTO.NarrativeDTO();
        solution.setDescription("Built an end-to-end procurement platform with automated requisition-to-purchase workflows, real-time inventory tracking with IoT sensors, vendor scorecards, and AI-powered demand forecasting to optimize material ordering and reduce waste.");
        solution.setPoints(List.of(
                "Automated PO processing reducing time to 4 hours",
                "IoT-enabled inventory tracking across 8 warehouses",
                "AI demand forecasting with 91% accuracy",
                "Vendor scorecards with automated performance rating",
                "Just-in-time delivery reducing overstock by 78%"
        ));
        dto.setSolution(solution);

        dto.setResults(List.of(
                buildResult("PO Processing Time", "4 Hours", "Down from 5 Days"),
                buildResult("Inventory Overstock", "$462K", "Down from $2.1M"),
                buildResult("Material Delays", "6%", "Down from 34%"),
                buildResult("Vendor Response Time", "8 Hours", "Down from 48 Hours")
        ));

        dto.setTechStack(List.of(
                buildTechStack("Frontend", List.of("Vue.js 3", "TypeScript", "Pinia", "Vuetify")),
                buildTechStack("Backend", List.of("Spring Boot", "Java 21", "REST APIs", "RabbitMQ")),
                buildTechStack("Infra", List.of("AWS", "PostgreSQL", "MongoDB", "Redis")),
                buildTechStack("Tools", List.of("Docker", "Kubernetes", "IoT Sensors", "Power BI"))
        ));

        dto.setProcess(List.of(
                buildProcess("Procurement Audit", "Vendor mapping, inventory analysis, PO workflow documentation, warehouse IoT assessment", "4"),
                buildProcess("Platform Design", "Requisition engine, vendor portal, inventory schema, forecasting model architecture", "5"),
                buildProcess("Development", "PO automation, IoT integration, vendor scoring, demand forecasting ML", "22"),
                buildProcess("Vendor Onboarding", "200+ vendor portal enrollment, training sessions, EDI integration with top 20 suppliers", "6")
        ));

        dto.setFeatures(List.of(
                buildFeature("Smart Requisition Engine", "Auto-generates material requisitions from BIM takeoffs and project schedules with approval routing, budget checks, and vendor pre-qualification"),
                buildFeature("IoT Inventory Tracking", "Real-time stock levels across 8 warehouses using RFID and weight sensors with automatic reorder triggers and location-based allocation"),
                buildFeature("AI Demand Forecasting", "Predictive model analyzing project schedules, weather patterns, and market prices to optimize order timing and quantities with 91% accuracy"),
                buildFeature("Vendor Intelligence Hub", "Automated scorecards tracking on-time delivery, quality ratings, price competitiveness, and compliance with real-time benchmarking against 200+ vendors")
        ));

        ProjectDTO.TestimonialDTO testimonial = new ProjectDTO.TestimonialDTO();
        testimonial.setQuote("ProcureMax eliminated our material chaos. We went from guessing what we need to knowing exactly what, when, and from whom. The $1.6M inventory reduction alone paid for the entire platform.");
        testimonial.setAuthor("David Chen");
        testimonial.setRole("Procurement Director, IronFrame Structures LLC");
        dto.setTestimonial(testimonial);

        return dto;
    }

    // ==================================================================================
    // PROJECT 4: HUMAN RESOURCES & LABOR MANAGEMENT
    // ==================================================================================
    private ProjectDTO buildHumanResourcesLaborProject() {
        ProjectDTO dto = new ProjectDTO();

        ProjectDTO.MetaDTO meta = new ProjectDTO.MetaDTO();
        meta.setTitle("WorkForce Pro - Construction Labor Management System");
        meta.setClient("MetroBuild Contractors");
        meta.setCategory("Human Resources & Labor Management");
        meta.setYear("2024");
        meta.setDuration("7 Months");
        meta.setRole("Backend Lead");
        meta.setWebsite("https://workforcepro.erp");
        dto.setMeta(meta);

        dto.setHeroImage("https://placehold.co/1200x600/3730a3/ffffff?text=WorkForce+Pro+Labor+Management");
        dto.setGallery(List.of(
                "https://placehold.co/800x500/e0e7ff/3730a3?text=Attendance+Tracker",
                "https://placehold.co/800x500/e0e7ff/3730a3?text=Skill+Matrix",
                "https://placehold.co/800x500/e0e7ff/3730a3?text=Payroll+Dashboard"
        ));

        ProjectDTO.NarrativeDTO challenge = new ProjectDTO.NarrativeDTO();
        challenge.setDescription("MetroBuild employed 1,200+ workers across 18 sites with paper-based time cards, manual payroll processing, and no skill tracking. Payroll errors cost $180K annually, union compliance violations were frequent, and finding qualified workers for specific tasks took days. Safety incidents were rising due to untrained workers on critical jobs.");
        challenge.setPoints(List.of(
                "1,200+ workers on paper time cards with 12% error rate",
                "$180K annual payroll error costs",
                "Union compliance violations and grievances",
                "No skill tracking — task assignment was guesswork",
                "Untrained workers on jobs causing safety incidents"
        ));
        dto.setChallenge(challenge);

        ProjectDTO.NarrativeDTO solution = new ProjectDTO.NarrativeDTO();
        solution.setDescription("Built a comprehensive labor management platform with biometric attendance, automated payroll with union rules, digital skill matrices, certification tracking with expiry alerts, and AI-powered crew optimization based on skills, location, and availability.");
        solution.setPoints(List.of(
                "Biometric attendance with GPS geofencing",
                "Automated payroll with 99.8% accuracy",
                "Digital skill matrix for 1,200+ workers",
                "Auto-certification expiry alerts and training scheduling",
                "AI crew optimizer matching skills to tasks"
        ));
        dto.setSolution(solution);

        dto.setResults(List.of(
                buildResult("Payroll Accuracy", "99.8%", "Up from 88%"),
                buildResult("Payroll Processing", "1 Day", "Down from 5 Days"),
                buildResult("Union Violations", "0", "Down from 14/Year"),
                buildResult("Crew Assignment Time", "2 Hours", "Down from 3 Days")
        ));

        dto.setTechStack(List.of(
                buildTechStack("Frontend", List.of("React", "TypeScript", "Redux Toolkit", "MUI")),
                buildTechStack("Backend", List.of("Spring Boot", "Java 21", "GraphQL", "Camunda")),
                buildTechStack("Infra", List.of("AWS", "PostgreSQL", "Redis", "Elasticsearch")),
                buildTechStack("Tools", List.of("Docker", "Kubernetes", "Biometric SDK", "Grafana"))
        ));

        dto.setProcess(List.of(
                buildProcess("Labor Audit", "Time card analysis, union rule documentation, skill assessment, safety training review", "3"),
                buildProcess("Architecture Design", "Biometric integration, payroll engine, skill matrix schema, compliance rule engine", "4"),
                buildProcess("Core Development", "Attendance system, payroll automation, skill tracking, certification alerts, crew optimizer", "18"),
                buildProcess("Site Rollout", "Biometric device installation, worker enrollment, superintendent training, union validation", "6")
        ));

        dto.setFeatures(List.of(
                buildFeature("Biometric Geo-Attendance", "Fingerprint/face recognition with GPS geofencing ensuring workers are on-site, with automatic overtime calculation and break compliance"),
                buildFeature("Union-Compliant Payroll Engine", "Automated payroll processing with 47 union rule sets, prevailing wage calculations, fringe benefits, and certified payroll report generation"),
                buildFeature("Dynamic Skill Matrix", "Real-time skill catalog for 1,200+ workers with certification levels, expiry tracking, and automatic training recommendations for gap closure"),
                buildFeature("AI Crew Optimizer", "Machine learning model matching worker skills, certifications, location, and availability to project tasks with optimal crew composition suggestions")
        ));

        ProjectDTO.TestimonialDTO testimonial = new ProjectDTO.TestimonialDTO();
        testimonial.setQuote("WorkForce Pro eliminated our payroll nightmare and gave us superpowers in crew management. We now assign the right people to the right jobs in minutes, not days. Union compliance is automatic.");
        testimonial.setAuthor("Robert Kim");
        testimonial.setRole("HR Director, MetroBuild Contractors");
        dto.setTestimonial(testimonial);

        return dto;
    }

    // ==================================================================================
    // PROJECT 5: EQUIPMENT & ASSET MANAGEMENT
    // ==================================================================================
    private ProjectDTO buildEquipmentAssetProject() {
        ProjectDTO dto = new ProjectDTO();

        ProjectDTO.MetaDTO meta = new ProjectDTO.MetaDTO();
        meta.setTitle("FleetGuard - Construction Equipment Management Platform");
        meta.setClient("HeavyLift Construction Corp");
        meta.setCategory("Equipment & Asset Management");
        meta.setYear("2024");
        meta.setDuration("8 Months");
        meta.setRole("IoT & Backend Engineer");
        meta.setWebsite("https://fleetguard.erp");
        dto.setMeta(meta);

        dto.setHeroImage("https://placehold.co/1200x600/14532d/ffffff?text=FleetGuard+Equipment+Management");
        dto.setGallery(List.of(
                "https://placehold.co/800x500/dcfce7/14532d?text=Equipment+Map",
                "https://placehold.co/800x500/dcfce7/14532d?text=Maintenance+Schedule",
                "https://placehold.co/800x500/dcfce7/14532d?text=Utilization+Analytics"
        ));

        ProjectDTO.NarrativeDTO challenge = new ProjectDTO.NarrativeDTO();
        challenge.setDescription("HeavyLift owned 340 pieces of heavy equipment across 22 sites with no centralized tracking. Equipment sat idle 40% of the time, maintenance was reactive causing $2.8M in breakdown costs, and rental decisions were made blindly. They lost 12 pieces of equipment to theft in 18 months with no recovery.");
        challenge.setPoints(List.of(
                "340 equipment pieces with no centralized tracking",
                "40% equipment idle time across 22 sites",
                "$2.8M in reactive breakdown maintenance costs",
                "12 equipment thefts with zero recovery",
                "Rental vs buy decisions made without data"
        ));
        dto.setChallenge(challenge);

        ProjectDTO.NarrativeDTO solution = new ProjectDTO.NarrativeDTO();
        solution.setDescription("Built an IoT-powered equipment management platform with GPS tracking, telematics integration, predictive maintenance using vibration and engine data, automated rental optimization, and geofence-based theft alerts with recovery coordination.");
        solution.setPoints(List.of(
                "GPS + telematics on all 340 equipment pieces",
                "Predictive maintenance reducing breakdowns by 72%",
                "Real-time utilization tracking across all sites",
                "Geofence theft alerts with 100% recovery rate",
                "Rental optimization saving $1.2M annually"
        ));
        dto.setSolution(solution);

        dto.setResults(List.of(
                buildResult("Equipment Utilization", "78%", "Up from 40%"),
                buildResult("Breakdown Costs", "$784K", "Down from $2.8M"),
                buildResult("Theft Recovery", "100%", "Up from 0%"),
                buildResult("Rental Savings", "$1.2M", "Annual Savings")
        ));

        dto.setTechStack(List.of(
                buildTechStack("Frontend", List.of("React", "TypeScript", "Mapbox GL", "Recharts")),
                buildTechStack("Backend", List.of("Spring Boot", "Java 21", "gRPC", "Apache Kafka")),
                buildTechStack("Infra", List.of("AWS IoT", "PostgreSQL", "InfluxDB", "Redis")),
                buildTechStack("Tools", List.of("Docker", "Kubernetes", "Telematics SDK", "Grafana"))
        ));

        dto.setProcess(List.of(
                buildProcess("Equipment Audit", "Asset inventory, telematics assessment, maintenance history analysis, theft incident review", "4"),
                buildProcess("IoT Architecture", "GPS device selection, telematics protocol design, data pipeline, predictive model specification", "5"),
                buildProcess("Platform Development", "Tracking dashboard, maintenance scheduler, utilization analytics, theft alert system", "20"),
                buildProcess("Fleet Integration", "340 device installations, operator training, maintenance team onboarding, geofence setup", "6")
        ));

        dto.setFeatures(List.of(
                buildFeature("Real-Time Equipment Tracking", "Live GPS location, engine hours, fuel levels, and operational status for all 340 pieces with site-based allocation and cross-site transfer optimization"),
                buildFeature("Predictive Maintenance AI", "Vibration, temperature, and engine data analysis predicting failures 72 hours in advance with automated work order generation and parts ordering"),
                buildFeature("Utilization Intelligence", "Cross-site equipment utilization analytics with idle time alerts, demand forecasting, and automated rental vs deploy recommendations"),
                buildFeature("Theft Recovery System", "Geofence alerts with instant SMS/email notifications, GPS tracking for recovery teams, and insurance claim automation with incident documentation")
        ));

        ProjectDTO.TestimonialDTO testimonial = new ProjectDTO.TestimonialDTO();
        testimonial.setQuote("FleetGuard turned our equipment from a cost center into a profit driver. We recovered every stolen piece, cut breakdowns by 72%, and finally know exactly where every asset is and how it's performing.");
        testimonial.setAuthor("Carlos Mendez");
        testimonial.setRole("Fleet Manager, HeavyLift Construction Corp");
        dto.setTestimonial(testimonial);

        return dto;
    }

    // ==================================================================================
    // PROJECT 6: DOCUMENT & CONTRACT MANAGEMENT
    // ==================================================================================
    private ProjectDTO buildDocumentContractProject() {
        ProjectDTO dto = new ProjectDTO();

        ProjectDTO.MetaDTO meta = new ProjectDTO.MetaDTO();
        meta.setTitle("DocVault - Construction Document Control System");
        meta.setClient("Summit Infrastructure Partners");
        meta.setCategory("Document & Contract Management");
        meta.setYear("2024");
        meta.setDuration("6 Months");
        meta.setRole("Full Stack Developer");
        meta.setWebsite("https://docvault.erp");
        dto.setMeta(meta);

        dto.setHeroImage("https://placehold.co/1200x600/581c87/ffffff?text=DocVault+Document+Control");
        dto.setGallery(List.of(
                "https://placehold.co/800x500/f3e8ff/581c87?text=Blueprint+Viewer",
                "https://placehold.co/800x500/f3e8ff/581c87?text=Contract+Tracker",
                "https://placehold.co/800x500/f3e8ff/581c87?text=Version+History"
        ));

        ProjectDTO.NarrativeDTO challenge = new ProjectDTO.NarrativeDTO();
        challenge.setDescription("Summit was drowning in 50,000+ documents across 30 projects stored in filing cabinets, shared drives, and email attachments. Teams worked from outdated blueprints causing $890K in rework, contract versions were impossible to track, and RFIs took 8 days average response time. Legal disputes were frequent due to missing documentation.");
        challenge.setPoints(List.of(
                "50,000+ documents scattered across 30 projects",
                "$890K in rework from outdated blueprint versions",
                "8-day average RFI response time",
                "Contract versions impossible to track",
                "Legal disputes from missing documentation"
        ));
        dto.setChallenge(challenge);

        ProjectDTO.NarrativeDTO solution = new ProjectDTO.NarrativeDTO();
        solution.setDescription("Built a centralized document control platform with cloud-based blueprint viewing, automated version control, digital RFI/submittal workflows, contract lifecycle management with e-signatures, and AI-powered document search across all project files.");
        solution.setPoints(List.of(
                "Cloud blueprint viewing with automatic version sync",
                "Digital RFI workflow reducing response to 18 hours",
                "Contract lifecycle with e-signature integration",
                "AI document search across 50,000+ files",
                "Automated audit trail for legal compliance"
        ));
        dto.setSolution(solution);

        dto.setResults(List.of(
                buildResult("RFI Response Time", "18 Hours", "Down from 8 Days"),
                buildResult("Rework Costs", "$45K", "Down from $890K"),
                buildResult("Document Search", "3 Seconds", "Down from 45 Minutes"),
                buildResult("Legal Disputes", "1", "Down from 8/Year")
        ));

        dto.setTechStack(List.of(
                buildTechStack("Frontend", List.of("React", "TypeScript", "PDF.js", "Fabric.js")),
                buildTechStack("Backend", List.of("Spring Boot", "Java 21", "REST APIs", "Elasticsearch")),
                buildTechStack("Infra", List.of("AWS S3", "PostgreSQL", "Redis", "CloudFront")),
                buildTechStack("Tools", List.of("Docker", "Kubernetes", "DocuSign API", "OCR Engine"))
        ));

        dto.setProcess(List.of(
                buildProcess("Document Audit", "50,000 file inventory, version analysis, RFI workflow mapping, contract storage review", "3"),
                buildProcess("Platform Design", "Document schema, version control logic, RFI workflow, search index architecture", "4"),
                buildProcess("Core Development", "Blueprint viewer, version control, RFI system, contract management, AI search", "16"),
                buildProcess("Migration & Training", "Legacy document migration, team training, e-signature rollout, compliance validation", "5")
        ));

        dto.setFeatures(List.of(
                buildFeature("Intelligent Blueprint Viewer", "Cloud-based DWG/PDF viewing with automatic version sync, markup tools, and side-by-side comparison ensuring all teams work from the latest drawings"),
                buildFeature("Digital RFI Engine", "Structured RFI creation with automatic routing, escalation rules, and response tracking reducing average response time from 8 days to 18 hours"),
                buildFeature("Contract Lifecycle Hub", "End-to-end contract management from draft to execution with version control, approval workflows, e-signatures, and automated compliance checking"),
                buildFeature("AI Document Search", "Natural language search across 50,000+ documents using OCR and NLP with instant results, context highlighting, and cross-reference linking")
        ));

        ProjectDTO.TestimonialDTO testimonial = new ProjectDTO.TestimonialDTO();
        testimonial.setQuote("DocVault eliminated our document chaos. We stopped working from wrong blueprints, RFIs fly through in hours, and our legal team sleeps better knowing every document is tracked and auditable.");
        testimonial.setAuthor("Lisa Anderson");
        testimonial.setRole("Document Control Manager, Summit Infrastructure Partners");
        dto.setTestimonial(testimonial);

        return dto;
    }

    // ==================================================================================
    // PROJECT 7: SUBCONTRACTOR & VENDOR PORTAL
    // ==================================================================================
    private ProjectDTO buildSubcontractorVendorProject() {
        ProjectDTO dto = new ProjectDTO();

        ProjectDTO.MetaDTO meta = new ProjectDTO.MetaDTO();
        meta.setTitle("SubConnect - Subcontractor Collaboration Platform");
        meta.setClient("Alliance General Contractors");
        meta.setCategory("Subcontractor & Vendor Portal");
        meta.setYear("2024");
        meta.setDuration("7 Months");
        meta.setRole("Backend Architect");
        meta.setWebsite("https://subconnect.erp");
        dto.setMeta(meta);

        dto.setHeroImage("https://placehold.co/1200x600/92400e/ffffff?text=SubConnect+Vendor+Portal");
        dto.setGallery(List.of(
                "https://placehold.co/800x500/fef3c7/92400e?text=Bid+Management",
                "https://placehold.co/800x500/fef3c7/92400e?text=Performance+Scorecard",
                "https://placehold.co/800x500/fef3c7/92400e?text=Payment+Portal"
        ));

        ProjectDTO.NarrativeDTO challenge = new ProjectDTO.NarrativeDTO();
        challenge.setDescription("Alliance worked with 180+ subcontractors across 25 projects with zero digital collaboration. Bid management was via email with 2-week turnaround, subcontractor performance was unmeasured, and payment disputes were constant with $3.4M in contested invoices. Safety incidents from unqualified subs were rising.");
        challenge.setPoints(List.of(
                "180+ subcontractors with zero digital collaboration",
                "2-week average bid turnaround time",
                "$3.4M in contested subcontractor invoices",
                "No performance tracking or benchmarking",
                "Safety incidents from unqualified subcontractors"
        ));
        dto.setChallenge(challenge);

        ProjectDTO.NarrativeDTO solution = new ProjectDTO.NarrativeDTO();
        solution.setDescription("Built a dedicated subcontractor portal with digital bid management, automated qualification checks, real-time performance scorecards, integrated payment workflows with lien waiver automation, and safety compliance tracking with training verification.");
        solution.setPoints(List.of(
                "Digital bid platform reducing turnaround to 3 days",
                "Automated sub qualification with insurance verification",
                "Real-time performance scorecards for 180+ subs",
                "Payment automation with lien waiver integration",
                "Safety compliance tracking with training alerts"
        ));
        dto.setSolution(solution);

        dto.setResults(List.of(
                buildResult("Bid Turnaround", "3 Days", "Down from 14 Days"),
                buildResult("Payment Disputes", "$120K", "Down from $3.4M"),
                buildResult("Sub Qualification Time", "2 Hours", "Down from 5 Days"),
                buildResult("Safety Incidents", "-68%", "Year Over Year")
        ));

        dto.setTechStack(List.of(
                buildTechStack("Frontend", List.of("React", "TypeScript", "Redux", "MUI")),
                buildTechStack("Backend", List.of("Spring Boot", "Java 21", "GraphQL", "Camunda")),
                buildTechStack("Infra", List.of("AWS", "PostgreSQL", "Redis", "Elasticsearch")),
                buildTechStack("Tools", List.of("Docker", "Kubernetes", "Stripe API", "DocuSign"))
        ));

        dto.setProcess(List.of(
                buildProcess("Subcontractor Audit", "180+ sub mapping, bid process analysis, payment dispute review, safety record assessment", "3"),
                buildProcess("Portal Design", "Bid engine, qualification schema, performance metrics, payment workflow, safety tracking", "4"),
                buildProcess("Core Development", "Bid platform, qualification system, scorecards, payment automation, safety compliance", "18"),
                buildProcess("Sub Onboarding", "180+ portal enrollment, training webinars, payment setup, qualification migration", "6")
        ));

        dto.setFeatures(List.of(
                buildFeature("Digital Bid Platform", "Structured bid creation with scope breakdown, automatic distribution to qualified subs, side-by-side comparison, and award recommendation engine"),
                buildFeature("Auto-Qualification Engine", "Automated insurance, bonding, license, and safety record verification with real-time status updates and renewal alerts for 180+ subcontractors"),
                buildFeature("Performance Intelligence", "Real-time scorecards tracking on-time completion, quality ratings, safety incidents, and change order frequency with automated benchmarking"),
                buildFeature("Integrated Payment Hub", "Automated payment processing with conditional lien waiver collection, retention tracking, and dispute resolution workflow reducing contested invoices by 96%")
        ));

        ProjectDTO.TestimonialDTO testimonial = new ProjectDTO.TestimonialDTO();
        testimonial.setQuote("SubConnect transformed our subcontractor relationships. Bids come in faster, payments are seamless, and we finally have data on who's delivering and who isn't. Our safety record improved dramatically.");
        testimonial.setAuthor("Thomas Wright");
        testimonial.setRole("Project Director, Alliance General Contractors");
        dto.setTestimonial(testimonial);

        return dto;
    }

    // ==================================================================================
    // PROJECT 8: QUALITY & SAFETY COMPLIANCE
    // ==================================================================================
    private ProjectDTO buildQualitySafetyProject() {
        ProjectDTO dto = new ProjectDTO();

        ProjectDTO.MetaDTO meta = new ProjectDTO.MetaDTO();
        meta.setTitle("SafeSite Pro - Quality & Safety Compliance Platform");
        meta.setClient("Guardian Construction Services");
        meta.setCategory("Quality & Safety Compliance");
        meta.setYear("2024");
        meta.setDuration("9 Months");
        meta.setRole("Full Stack Lead");
        meta.setWebsite("https://safesitepro.erp");
        dto.setMeta(meta);

        dto.setHeroImage("https://placehold.co/1200x600/be123c/ffffff?text=SafeSite+Pro+Safety+Compliance");
        dto.setGallery(List.of(
                "https://placehold.co/800x500/ffe4e6/be123c?text=Inspection+Checklist",
                "https://placehold.co/800x500/ffe4e6/be123c?text=Incident+Report",
                "https://placehold.co/800x500/ffe4e6/be123c?text=Training+Dashboard"
        ));

        ProjectDTO.NarrativeDTO challenge = new ProjectDTO.NarrativeDTO();
        challenge.setDescription("Guardian had 32 active sites with paper-based safety inspections, no incident tracking system, and inconsistent OSHA compliance. They faced $2.1M in OSHA fines over 3 years, lost-time incidents were 4x industry average, and quality defects caused $1.4M in rework. Training records were scattered and expired certifications went unnoticed.");
        challenge.setPoints(List.of(
                "$2.1M in OSHA fines over 3 years",
                "Lost-time incidents 4x industry average",
                "$1.4M in quality defect rework costs",
                "Paper safety inspections with no follow-up",
                "Expired certifications going unnoticed"
        ));
        dto.setChallenge(challenge);

        ProjectDTO.NarrativeDTO solution = new ProjectDTO.NarrativeDTO();
        solution.setDescription("Built a comprehensive safety and quality platform with mobile inspection checklists, real-time incident reporting with photo/video, automated OSHA compliance tracking, AI-powered hazard prediction, and integrated training management with certification expiry alerts.");
        solution.setPoints(List.of(
                "Mobile inspection checklists with photo evidence",
                "Real-time incident reporting with auto-escalation",
                "AI hazard prediction with 87% accuracy",
                "Automated OSHA compliance dashboard",
                "Training management with auto-cert alerts"
        ));
        dto.setSolution(solution);

        dto.setResults(List.of(
                buildResult("OSHA Fines", "$0", "Down from $2.1M/3yr"),
                buildResult("Lost-Time Incidents", "-78%", "Year Over Year"),
                buildResult("Rework Costs", "$180K", "Down from $1.4M"),
                buildResult("Inspection Compliance", "98%", "Up from 54%")
        ));

        dto.setTechStack(List.of(
                buildTechStack("Frontend", List.of("React Native", "React", "TypeScript", "Redux")),
                buildTechStack("Backend", List.of("Spring Boot", "Java 21", "REST APIs", "Apache Kafka")),
                buildTechStack("Infra", List.of("AWS", "PostgreSQL", "MongoDB", "Redis")),
                buildTechStack("Tools", List.of("Docker", "Kubernetes", "TensorFlow", "Grafana"))
        ));

        dto.setProcess(List.of(
                buildProcess("Safety Audit", "OSHA violation analysis, incident review, inspection process mapping, training record audit", "4"),
                buildProcess("Platform Design", "Checklist engine, incident schema, compliance rule engine, hazard prediction model, training system", "5"),
                buildProcess("Core Development", "Mobile inspections, incident reporting, compliance dashboard, AI hazard prediction, training hub", "22"),
                buildProcess("Site Rollout", "32 site deployments, safety manager training, worker onboarding, OSHA validation", "8")
        ));

        dto.setFeatures(List.of(
                buildFeature("Mobile Inspection Engine", "Digital checklists with photo/video evidence, GPS tagging, automatic deficiency creation, and corrective action tracking across all 32 sites with offline capability"),
                buildFeature("Real-Time Incident Command", "Instant incident reporting with severity auto-classification, emergency contact alerts, investigation workflow, and root cause analysis with trend prediction"),
                buildFeature("AI Hazard Predictor", "Computer vision and sensor data analysis predicting safety hazards 48 hours in advance with 87% accuracy and automated mitigation task generation"),
                buildFeature("Compliance & Training Hub", "Automated OSHA regulation tracking, certification expiry alerts with 30/60/90-day warnings, and integrated training scheduling with completion verification")
        ));

        ProjectDTO.TestimonialDTO testimonial = new ProjectDTO.TestimonialDTO();
        testimonial.setQuote("SafeSite Pro saved lives and saved our company. We went from OSHA's target list to their recognition program. Our workers go home safe every day, and that's priceless.");
        testimonial.setAuthor("James Harrison");
        testimonial.setRole("Safety Director, Guardian Construction Services");
        dto.setTestimonial(testimonial);

        return dto;
    }

    // ==================================================================================
    // PROJECT 9: REPORTING & BUSINESS INTELLIGENCE
    // ==================================================================================
    private ProjectDTO buildReportingBIProject() {
        ProjectDTO dto = new ProjectDTO();

        ProjectDTO.MetaDTO meta = new ProjectDTO.MetaDTO();
        meta.setTitle("InsightHub - Construction Intelligence Dashboard");
        meta.setClient("Titan Development Group");
        meta.setCategory("Reporting & Business Intelligence");
        meta.setYear("2024");
        meta.setDuration("6 Months");
        meta.setRole("Data Engineer & Full Stack");
        meta.setWebsite("https://insighthub.erp");
        dto.setMeta(meta);

        dto.setHeroImage("https://placehold.co/1200x600/1e40af/ffffff?text=InsightHub+BI+Dashboard");
        dto.setGallery(List.of(
                "https://placehold.co/800x500/dbeafe/1e40af?text=Executive+Dashboard",
                "https://placehold.co/800x500/dbeafe/1e40af?text=Project+Analytics",
                "https://placehold.co/800x500/dbeafe/1e40af?text=Forecasting+Model"
        ));

        ProjectDTO.NarrativeDTO challenge = new ProjectDTO.NarrativeDTO();
        challenge.setDescription("Titan operated 40 projects with data trapped in 12 disconnected systems. Executive reports took 3 weeks to compile, project profitability was a mystery until closeout, and forecasting was based on gut feeling. They missed early warning signs on 6 projects that went $8M over budget combined.");
        challenge.setPoints(List.of(
                "12 disconnected systems with no data integration",
                "3-week executive report compilation time",
                "Project profitability unknown until closeout",
                "6 projects went $8M over budget undetected",
                "Forecasting based on intuition, not data"
        ));
        dto.setChallenge(challenge);

        ProjectDTO.NarrativeDTO solution = new ProjectDTO.NarrativeDTO();
        solution.setDescription("Built a unified business intelligence platform with real-time data pipelines from all 12 systems, executive dashboards with drill-down capability, AI-powered project risk scoring, automated profitability forecasting, and customizable reports for every stakeholder level.");
        solution.setPoints(List.of(
                "Unified data pipeline from 12 source systems",
                "Real-time executive dashboards with drill-down",
                "AI risk scoring predicting overruns 30 days early",
                "Automated profitability forecasting per project",
                "Self-service report builder for all stakeholders"
        ));
        dto.setSolution(solution);

        dto.setResults(List.of(
                buildResult("Report Generation", "Real-Time", "Down from 3 Weeks"),
                buildResult("Budget Overrun Detection", "30 Days", "Early Warning"),
                buildResult("Forecast Accuracy", "92%", "Up from 48%"),
                buildResult("Executive Decision Speed", "Same Day", "Down from 3 Weeks")
        ));

        dto.setTechStack(List.of(
                buildTechStack("Frontend", List.of("React", "TypeScript", "D3.js", "AG Grid")),
                buildTechStack("Backend", List.of("Spring Boot", "Java 21", "GraphQL", "Apache Airflow")),
                buildTechStack("Infra", List.of("AWS", "Snowflake", "PostgreSQL", "Redis")),
                buildTechStack("Tools", List.of("Docker", "Kubernetes", "dbt", "Tableau"))
        ));

        dto.setProcess(List.of(
                buildProcess("Data Audit", "12 system inventory, schema mapping, data quality assessment, KPI definition with executives", "3"),
                buildProcess("Pipeline Architecture", "ETL design, data warehouse schema, real-time streaming, ML model specification", "4"),
                buildProcess("Platform Development", "Data pipelines, dashboard engine, risk scoring ML, forecasting models, report builder", "16"),
                buildProcess("Stakeholder Rollout", "Executive training, department-specific dashboards, automated report scheduling", "5")
        ));

        dto.setFeatures(List.of(
                buildFeature("Unified Data Lake", "Real-time ETL pipelines ingesting data from 12 source systems into a unified warehouse with automated data quality checks and anomaly detection"),
                buildFeature("Executive Command Center", "Real-time portfolio dashboard showing project health, financial performance, resource allocation, and risk heatmaps with one-click drill-down to any detail"),
                buildFeature("AI Risk Scorer", "Machine learning model analyzing 200+ project signals to predict budget and schedule overruns 30 days in advance with 92% accuracy and automated alert routing"),
                buildFeature("Self-Service Report Builder", "Drag-and-drop report creation with 50+ pre-built templates, automated scheduling, and distribution to stakeholders with role-based data access controls")
        ));

        ProjectDTO.TestimonialDTO testimonial = new ProjectDTO.TestimonialDTO();
        testimonial.setQuote("InsightHub gave us the visibility we always needed but never had. We now see problems 30 days before they become crises. Our board presentations went from defensive to proactive.");
        testimonial.setAuthor("Richard Stone");
        testimonial.setRole("CEO, Titan Development Group");
        dto.setTestimonial(testimonial);

        return dto;
    }

    // ==================================================================================
    // PROJECT 10: CRM & CLIENT MANAGEMENT
    // ==================================================================================
    private ProjectDTO buildCRMClientProject() {
        ProjectDTO dto = new ProjectDTO();

        ProjectDTO.MetaDTO meta = new ProjectDTO.MetaDTO();
        meta.setTitle("ClientEdge - Construction CRM & Client Portal");
        meta.setClient("Premier Commercial Builders");
        meta.setCategory("CRM & Client Management");
        meta.setYear("2024");
        meta.setDuration("5 Months");
        meta.setRole("Full Stack Developer");
        meta.setWebsite("https://clientedge.erp");
        dto.setMeta(meta);

        dto.setHeroImage("https://placehold.co/1200x600/0e7490/ffffff?text=ClientEdge+CRM+Portal");
        dto.setGallery(List.of(
                "https://placehold.co/800x500/cffafe/0e7490?text=Client+Dashboard",
                "https://placehold.co/800x500/cffafe/0e7490?text=Bid+Proposal",
                "https://placehold.co/800x500/cffafe/0e7490?text=Warranty+Portal"
        ));

        ProjectDTO.NarrativeDTO challenge = new ProjectDTO.NarrativeDTO();
        challenge.setDescription("Premier had 200+ client relationships managed in individual spreadsheets with no central CRM. Bid proposals took 2 weeks to prepare, client communication was inconsistent, and post-project warranty requests were lost in email. They lost 3 major clients to competitors with better communication and had no visibility into client satisfaction.");
        challenge.setPoints(List.of(
                "200+ client relationships in individual spreadsheets",
                "2-week bid proposal preparation time",
                "Warranty requests lost in email threads",
                "Lost 3 major clients to competitors",
                "Zero client satisfaction tracking"
        ));
        dto.setChallenge(challenge);

        ProjectDTO.NarrativeDTO solution = new ProjectDTO.NarrativeDTO();
        solution.setDescription("Built a construction-focused CRM with automated bid proposal generation, client communication tracking, a self-service client portal for project updates, integrated warranty management with SLA tracking, and NPS-based satisfaction monitoring with automated follow-up workflows.");
        solution.setPoints(List.of(
                "Automated bid proposals reducing time to 4 hours",
                "Client self-service portal with real-time updates",
                "Warranty ticket system with SLA tracking",
                "NPS satisfaction monitoring with auto-follow-up",
                "Pipeline forecasting with 88% win-rate accuracy"
        ));
        dto.setSolution(solution);

        dto.setResults(List.of(
                buildResult("Bid Preparation", "4 Hours", "Down from 2 Weeks"),
                buildResult("Client Retention", "96%", "Up from 78%"),
                buildResult("Warranty Resolution", "24 Hours", "Down from 14 Days"),
                buildResult("Win Rate", "68%", "Up from 42%")
        ));

        dto.setTechStack(List.of(
                buildTechStack("Frontend", List.of("React", "TypeScript", "Redux", "MUI")),
                buildTechStack("Backend", List.of("Spring Boot", "Java 21", "GraphQL", "Camunda")),
                buildTechStack("Infra", List.of("AWS", "PostgreSQL", "Redis", "Elasticsearch")),
                buildTechStack("Tools", List.of("Docker", "Kubernetes", "SendGrid", "Zapier"))
        ));

        dto.setProcess(List.of(
                buildProcess("CRM Audit", "Client relationship mapping, bid process analysis, warranty workflow review, communication audit", "3"),
                buildProcess("Platform Design", "CRM schema, proposal engine, portal architecture, warranty system, satisfaction tracking", "3"),
                buildProcess("Core Development", "CRM hub, bid automation, client portal, warranty tickets, NPS system, forecasting", "14"),
                buildProcess("Client Onboarding", "200+ client portal invites, training sessions, warranty migration, satisfaction baseline", "4")
        ));

        dto.setFeatures(List.of(
                buildFeature("Auto-Proposal Engine", "AI-powered bid proposal generation from project specs with automatic pricing, scope breakdown, timeline, and contract terms reducing preparation from 2 weeks to 4 hours"),
                buildFeature("Client Self-Service Portal", "Real-time project dashboards for clients showing progress photos, schedule updates, budget status, and document access with automated notification preferences"),
                buildFeature("Warranty Command Center", "Digital warranty ticket system with photo upload, SLA tracking, auto-assignment to trades, and client communication threads ensuring 24-hour resolution targets"),
                buildFeature("Client Intelligence Hub", "NPS satisfaction tracking with automated follow-up workflows, churn risk prediction, and relationship health scoring for 200+ clients with proactive engagement recommendations")
        ));

        ProjectDTO.TestimonialDTO testimonial = new ProjectDTO.TestimonialDTO();
        testimonial.setQuote("ClientEdge transformed how we win and keep clients. Our proposals are now same-day, clients have real-time visibility, and we've won back the trust we lost. Our win rate jumped from 42% to 68%.");
        testimonial.setAuthor("Amanda Foster");
        testimonial.setRole("Business Development Director, Premier Commercial Builders");
        dto.setTestimonial(testimonial);

        return dto;
    }

    // ==================================================================================
    // BUILDER HELPER METHODS
    // ==================================================================================

    private ProjectDTO.ResultDTO buildResult(String metric, String value, String period) {
        ProjectDTO.ResultDTO r = new ProjectDTO.ResultDTO();
        r.setMetric(metric);
        r.setValue(value);
        r.setPeriod(period);
        return r;
    }

    private ProjectDTO.TechStackDTO buildTechStack(String category, List<String> tools) {
        ProjectDTO.TechStackDTO t = new ProjectDTO.TechStackDTO();
        t.setCategory(category);
        t.setTools(tools);
        return t;
    }

    private ProjectDTO.ProcessDTO buildProcess(String phase, String desc, String weeks) {
        ProjectDTO.ProcessDTO p = new ProjectDTO.ProcessDTO();
        p.setPhase(phase);
        p.setDesc(desc);
        p.setWeeks(weeks);
        return p;
    }

    private ProjectDTO.FeatureDTO buildFeature(String title, String desc) {
        ProjectDTO.FeatureDTO f = new ProjectDTO.FeatureDTO();
        f.setTitle(title);
        f.setDesc(desc);
        return f;
    }
}