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
        log.info("Initializing Case Study sample data...");
        createAllProjects();
        log.info("Sample data initialization complete. Total projects: {}", projectRepository.count());
    }

    private void createAllProjects() {
        caseStudyProjectService.saveCaseStudy(buildEcommerceProject());
        caseStudyProjectService.saveCaseStudy(buildFintechProject());
        caseStudyProjectService.saveCaseStudy(buildHealthcareProject());
        caseStudyProjectService.saveCaseStudy(buildSaasProject());
        caseStudyProjectService.saveCaseStudy(buildAiMlProject());
        caseStudyProjectService.saveCaseStudy(buildLogisticsProject());
    }

    // ==================================================================================
    // PROJECT 1: E-COMMERCE
    // ==================================================================================
    private ProjectDTO buildEcommerceProject() {
        ProjectDTO dto = new ProjectDTO();

        // Meta
        ProjectDTO.MetaDTO meta = new ProjectDTO.MetaDTO();
        meta.setTitle("ShopNova - Next-Gen E-Commerce Platform");
        meta.setClient("ShopNova Inc.");
        meta.setCategory("E-Commerce");
        meta.setYear("2024");
        meta.setDuration("8 Months");
        meta.setRole("Full Stack Lead");
        meta.setWebsite("https://shopnova.com");
        dto.setMeta(meta);

        dto.setHeroImage("https://placehold.co/1200x600/047857/ffffff?text=ShopNova+Platform");
        dto.setGallery(List.of(
                "https://placehold.co/800x500/ecfdf5/047857?text=Dashboard",
                "https://placehold.co/800x500/ecfdf5/047857?text=Product+Page",
                "https://placehold.co/800x500/ecfdf5/047857?text=Checkout+Flow"
        ));

        // Challenge
        ProjectDTO.NarrativeDTO challenge = new ProjectDTO.NarrativeDTO();
        challenge.setDescription("ShopNova was running on a legacy monolithic PHP system that couldn't handle their growing 500K+ daily users. Page load times exceeded 8 seconds, cart abandonment was at 73%, and every sale event caused server crashes.");
        challenge.setPoints(List.of(
                "8+ second page load times killing conversions",
                "73% cart abandonment rate on mobile",
                "Server crashes during flash sales",
                "No real-time inventory management",
                "Zero personalization capabilities"
        ));
        dto.setChallenge(challenge);

        // Solution
        ProjectDTO.NarrativeDTO solution = new ProjectDTO.NarrativeDTO();
        solution.setDescription("We rebuilt the entire platform on a microservices architecture using Next.js for the frontend with SSR/ISR, migrated to a distributed backend with Spring Boot, and implemented Redis caching with Elasticsearch for lightning-fast search.");
        solution.setPoints(List.of(
                "Migrated to Next.js with SSR reducing load time to 1.2s",
                "Implemented Redis caching for product catalog",
                "Built real-time inventory sync with WebSockets",
                "AI-powered product recommendation engine",
                "Auto-scaling Kubernetes cluster for peak loads"
        ));
        dto.setSolution(solution);

        // Results
        dto.setResults(List.of(
                buildResult("Revenue Increase", "+245%", "First 6 Months"),
                buildResult("Page Load Time", "1.2s", "Down from 8s"),
                buildResult("Cart Abandonment", "-41%", "Post Launch"),
                buildResult("Uptime", "99.99%", "Year Round")
        ));

        // Tech Stack
        dto.setTechStack(List.of(
                buildTechStack("Frontend", List.of("Next.js 14", "TypeScript", "Tailwind CSS", "Redux Toolkit")),
                buildTechStack("Backend", List.of("Spring Boot 3", "Node.js", "GraphQL", "REST APIs")),
                buildTechStack("Infra", List.of("AWS EKS", "Redis", "PostgreSQL", "Elasticsearch")),
                buildTechStack("Tools", List.of("Docker", "Kubernetes", "GitHub Actions", "Datadog"))
        ));

        // Process
        dto.setProcess(List.of(
                buildProcess("Discovery & Audit", "Full system audit, stakeholder interviews, performance benchmarking of existing platform", "3"),
                buildProcess("Architecture Design", "Microservices blueprint, database schema design, API contract definition", "4"),
                buildProcess("Development", "Parallel frontend/backend development with bi-weekly sprint reviews", "20"),
                buildProcess("Testing & Launch", "Load testing with 1M concurrent users, phased rollout with feature flags", "5")
        ));

        // Features
        dto.setFeatures(List.of(
                buildFeature("AI Product Recommendations", "Machine learning model trained on 2M purchase histories delivering personalized recommendations with 34% click-through rate"),
                buildFeature("Real-Time Inventory", "WebSocket-powered inventory updates across 50+ warehouses with automatic reorder triggers"),
                buildFeature("One-Click Checkout", "Saved payment methods, address auto-fill, and instant order confirmation reducing checkout to under 30 seconds"),
                buildFeature("Advanced Analytics Dashboard", "Real-time sales metrics, customer behavior heatmaps, and automated revenue forecasting")
        ));

        // Testimonial
        ProjectDTO.TestimonialDTO testimonial = new ProjectDTO.TestimonialDTO();
        testimonial.setQuote("The transformation was beyond our expectations. We went from dreading sale events to looking forward to them. The platform handled our Black Friday traffic of 2 million concurrent users without breaking a sweat.");
        testimonial.setAuthor("Sarah Chen");
        testimonial.setRole("CTO, ShopNova Inc.");
        dto.setTestimonial(testimonial);

        return dto;
    }

    // ==================================================================================
    // PROJECT 2: FINTECH
    // ==================================================================================
    private ProjectDTO buildFintechProject() {
        ProjectDTO dto = new ProjectDTO();

        ProjectDTO.MetaDTO meta = new ProjectDTO.MetaDTO();
        meta.setTitle("PayFlow - Real-Time Payment Infrastructure");
        meta.setClient("PayFlow Financial");
        meta.setCategory("Fintech");
        meta.setYear("2024");
        meta.setDuration("12 Months");
        meta.setRole("Backend Architect");
        meta.setWebsite("https://payflow.io");
        dto.setMeta(meta);

        dto.setHeroImage("https://placehold.co/1200x600/1e40af/ffffff?text=PayFlow+Infrastructure");
        dto.setGallery(List.of(
                "https://placehold.co/800x500/eff6ff/1e40af?text=Transaction+Dashboard",
                "https://placehold.co/800x500/eff6ff/1e40af?text=Fraud+Detection",
                "https://placehold.co/800x500/eff6ff/1e40af?text=Analytics"
        ));

        ProjectDTO.NarrativeDTO challenge = new ProjectDTO.NarrativeDTO();
        challenge.setDescription("PayFlow needed to process 10,000+ transactions per second across 47 countries while maintaining PCI-DSS Level 1 compliance, sub-100ms latency, and 99.999% uptime. Their legacy batch-processing system had a 3-hour settlement window unacceptable for modern finance.");
        challenge.setPoints(List.of(
                "3-hour settlement window losing enterprise clients",
                "Unable to process more than 500 TPS",
                "No real-time fraud detection capabilities",
                "Compliance gaps across multiple jurisdictions",
                "Single point of failure in payment routing"
        ));
        dto.setChallenge(challenge);

        ProjectDTO.NarrativeDTO solution = new ProjectDTO.NarrativeDTO();
        solution.setDescription("Built an event-driven payment processing engine using Apache Kafka for message streaming, implemented real-time fraud detection with ML models, and deployed a multi-region active-active architecture achieving true financial-grade reliability.");
        solution.setPoints(List.of(
                "Event-driven architecture with Apache Kafka processing 15K TPS",
                "ML fraud detection with 99.7% accuracy, <50ms response",
                "Multi-region active-active deployment across 6 AWS regions",
                "Automated PCI-DSS compliance reporting",
                "Instant settlement with real-time reconciliation"
        ));
        dto.setSolution(solution);

        dto.setResults(List.of(
                buildResult("Transaction Speed", "15K TPS", "Peak Capacity"),
                buildResult("Settlement Time", "<2 sec", "Down from 3 Hours"),
                buildResult("Fraud Prevention", "$12M Saved", "Annually"),
                buildResult("System Uptime", "99.999%", "18 Months Running")
        ));

        dto.setTechStack(List.of(
                buildTechStack("Frontend", List.of("React", "TypeScript", "D3.js", "Material UI")),
                buildTechStack("Backend", List.of("Java 21", "Spring Boot", "Apache Kafka", "gRPC")),
                buildTechStack("Infra", List.of("AWS Multi-Region", "CockroachDB", "Redis Cluster", "HashiCorp Vault")),
                buildTechStack("Tools", List.of("Terraform", "Prometheus", "Grafana", "PagerDuty"))
        ));

        dto.setProcess(List.of(
                buildProcess("Compliance & Architecture", "PCI-DSS gap analysis, threat modeling, multi-region architecture design", "6"),
                buildProcess("Core Engine Development", "Payment processor, Kafka event streams, fraud detection model training", "24"),
                buildProcess("Integration & Testing", "Bank API integrations, chaos engineering, penetration testing", "8"),
                buildProcess("Phased Rollout", "5% → 25% → 100% traffic migration with real-time monitoring", "10")
        ));

        dto.setFeatures(List.of(
                buildFeature("Real-Time Fraud Detection", "Ensemble ML model analyzing 200+ signals per transaction with adaptive learning that improves with every flagged transaction"),
                buildFeature("Smart Payment Routing", "Dynamic routing across 12 payment networks selecting the fastest and cheapest path for each transaction automatically"),
                buildFeature("Multi-Currency Engine", "Real-time FX rates for 150+ currencies with automatic hedging to protect against exchange rate volatility"),
                buildFeature("Compliance Automation", "Automated AML screening, KYC verification, and regulatory reporting across 47 jurisdictions")
        ));

        ProjectDTO.TestimonialDTO testimonial = new ProjectDTO.TestimonialDTO();
        testimonial.setQuote("PayFlow's new infrastructure is the backbone of our growth. We onboarded 3 Fortune 500 clients in the month after launch — clients who had specifically rejected us before due to our settlement times.");
        testimonial.setAuthor("Marcus Williams");
        testimonial.setRole("CEO, PayFlow Financial");
        dto.setTestimonial(testimonial);

        return dto;
    }

    // ==================================================================================
    // PROJECT 3: HEALTHCARE
    // ==================================================================================
    private ProjectDTO buildHealthcareProject() {
        ProjectDTO dto = new ProjectDTO();

        ProjectDTO.MetaDTO meta = new ProjectDTO.MetaDTO();
        meta.setTitle("MediConnect - Patient Care Platform");
        meta.setClient("MediConnect Health Systems");
        meta.setCategory("Healthcare");
        meta.setYear("2023");
        meta.setDuration("10 Months");
        meta.setRole("Technical Lead");
        meta.setWebsite("https://mediconnect.health");
        dto.setMeta(meta);

        dto.setHeroImage("https://placehold.co/1200x600/7c3aed/ffffff?text=MediConnect+Platform");
        dto.setGallery(List.of(
                "https://placehold.co/800x500/f5f3ff/7c3aed?text=Patient+Dashboard",
                "https://placehold.co/800x500/f5f3ff/7c3aed?text=Telemedicine",
                "https://placehold.co/800x500/f5f3ff/7c3aed?text=EHR+System"
        ));

        ProjectDTO.NarrativeDTO challenge = new ProjectDTO.NarrativeDTO();
        challenge.setDescription("MediConnect's 200+ hospitals were using 14 different incompatible EHR systems. Patient data was siloed, referrals took 3-5 days via fax, and doctors had no visibility into patient history across facilities. HIPAA compliance was inconsistent across locations.");
        challenge.setPoints(List.of(
                "14 incompatible EHR systems with no data sharing",
                "3-5 day referral process done via fax",
                "No unified patient identity across facilities",
                "Inconsistent HIPAA compliance posture",
                "Zero telemedicine capabilities post-COVID demand surge"
        ));
        dto.setChallenge(challenge);

        ProjectDTO.NarrativeDTO solution = new ProjectDTO.NarrativeDTO();
        solution.setDescription("Designed and built a unified healthcare platform using FHIR R4 standards for interoperability, a Master Patient Index for unified identity, HIPAA-compliant telemedicine with WebRTC, and an AI-assisted clinical decision support system.");
        solution.setPoints(List.of(
                "FHIR R4 integration layer connecting all 14 EHR systems",
                "Master Patient Index unifying 2.3M patient records",
                "HIPAA-compliant telemedicine with <200ms video latency",
                "AI clinical decision support reducing diagnostic errors",
                "Automated referral system reducing wait time to 2 hours"
        ));
        dto.setSolution(solution);

        dto.setResults(List.of(
                buildResult("Referral Time", "2 Hours", "Down from 5 Days"),
                buildResult("Patient Records Unified", "2.3M", "Across All Facilities"),
                buildResult("Telemedicine Visits", "+680%", "Year Over Year"),
                buildResult("Diagnostic Accuracy", "+23%", "With AI Assist")
        ));

        dto.setTechStack(List.of(
                buildTechStack("Frontend", List.of("React Native", "React.js", "WebRTC", "Chart.js")),
                buildTechStack("Backend", List.of("Spring Boot", "HAPI FHIR", "Python", "FastAPI")),
                buildTechStack("Infra", List.of("AWS GovCloud", "PostgreSQL", "MongoDB", "RabbitMQ")),
                buildTechStack("Tools", List.of("Vault", "AWS KMS", "SonarQube", "OWASP ZAP"))
        ));

        dto.setProcess(List.of(
                buildProcess("HIPAA Compliance Design", "Security architecture, data classification, encryption strategy, BAA agreements", "4"),
                buildProcess("FHIR Integration Layer", "HL7 to FHIR transformation, EHR adapter development, patient matching algorithm", "16"),
                buildProcess("Platform Development", "Telemedicine, scheduling, clinical decision support, mobile apps", "18"),
                buildProcess("Validation & Go-Live", "Clinical validation, staff training, facility-by-facility rollout", "6")
        ));

        dto.setFeatures(List.of(
                buildFeature("Unified Patient Timeline", "Complete longitudinal patient view aggregating data from all facilities, labs, pharmacies, and wearables in a single chronological timeline"),
                buildFeature("AI Clinical Decision Support", "NLP-powered analysis of clinical notes suggesting diagnoses, drug interactions, and preventive care opportunities"),
                buildFeature("Telemedicine Suite", "HD video consultations with integrated prescription, lab ordering, and EHR documentation in a single workflow"),
                buildFeature("Smart Referral Network", "Automated specialist matching based on condition, insurance, location, and availability with instant appointment booking")
        ));

        ProjectDTO.TestimonialDTO testimonial = new ProjectDTO.TestimonialDTO();
        testimonial.setQuote("MediConnect transformed how our hospitals collaborate. A patient who collapses in one of our ERs now has their complete medical history available to the treating physician within seconds. This directly saves lives.");
        testimonial.setAuthor("Dr. Priya Sharma");
        testimonial.setRole("Chief Medical Officer, MediConnect Health");
        dto.setTestimonial(testimonial);

        return dto;
    }

    // ==================================================================================
    // PROJECT 4: SAAS
    // ==================================================================================
    private ProjectDTO buildSaasProject() {
        ProjectDTO dto = new ProjectDTO();

        ProjectDTO.MetaDTO meta = new ProjectDTO.MetaDTO();
        meta.setTitle("TaskFlow - Enterprise Project Management SaaS");
        meta.setClient("TaskFlow Technologies");
        meta.setCategory("SaaS");
        meta.setYear("2024");
        meta.setDuration("6 Months");
        meta.setRole("Full Stack Developer");
        meta.setWebsite("https://taskflow.app");
        dto.setMeta(meta);

        dto.setHeroImage("https://placehold.co/1200x600/0369a1/ffffff?text=TaskFlow+SaaS");
        dto.setGallery(List.of(
                "https://placehold.co/800x500/f0f9ff/0369a1?text=Kanban+Board",
                "https://placehold.co/800x500/f0f9ff/0369a1?text=Timeline+View",
                "https://placehold.co/800x500/f0f9ff/0369a1?text=Team+Analytics"
        ));

        ProjectDTO.NarrativeDTO challenge = new ProjectDTO.NarrativeDTO();
        challenge.setDescription("TaskFlow wanted to compete in the saturated project management market dominated by Asana and Monday.com. They needed a differentiated product with enterprise-grade features, superior performance, and a freemium model that converts at industry-beating rates.");
        challenge.setPoints(List.of(
                "Competing against well-funded incumbents (Asana, Monday.com)",
                "Need for real-time collaboration for 10K+ concurrent users",
                "Complex permission hierarchy for enterprise clients",
                "Freemium conversion optimization challenge",
                "Multi-tenant architecture with strict data isolation"
        ));
        dto.setChallenge(challenge);

        ProjectDTO.NarrativeDTO solution = new ProjectDTO.NarrativeDTO();
        solution.setDescription("Built a differentiated SaaS platform with AI-powered project insights, a revolutionary offline-first architecture using CRDTs for conflict-free collaboration, and a data-driven onboarding flow that achieved 34% freemium-to-paid conversion.");
        solution.setPoints(List.of(
                "CRDT-based offline-first architecture for conflict-free sync",
                "AI project health scoring and deadline risk prediction",
                "Granular RBAC with custom role builder for enterprise",
                "A/B tested onboarding achieving 34% conversion rate",
                "Multi-tenant PostgreSQL with row-level security"
        ));
        dto.setSolution(solution);

        dto.setResults(List.of(
                buildResult("Paying Customers", "12,000+", "Within 6 Months"),
                buildResult("Freemium Conversion", "34%", "Industry Avg: 4%"),
                buildResult("NPS Score", "78", "World Class Rating"),
                buildResult("MRR Growth", "+180%", "Month over Month")
        ));

        dto.setTechStack(List.of(
                buildTechStack("Frontend", List.of("Vue.js 3", "TypeScript", "Pinia", "Vite")),
                buildTechStack("Backend", List.of("Spring Boot 3", "WebFlux", "GraphQL", "Yjs CRDT")),
                buildTechStack("Infra", List.of("GCP", "Cloud Spanner", "Pub/Sub", "Cloud Run")),
                buildTechStack("Tools", List.of("LaunchDarkly", "Mixpanel", "Sentry", "Stripe"))
        ));

        dto.setProcess(List.of(
                buildProcess("Product Strategy", "Competitive analysis, ICP definition, feature prioritization with RICE framework", "3"),
                buildProcess("UX & Design System", "User research, Figma prototyping, design system with 200+ components", "4"),
                buildProcess("Core Platform Build", "Multi-tenant backend, real-time sync engine, AI features, billing integration", "16"),
                buildProcess("Growth & Launch", "Beta program, Product Hunt launch, onboarding optimization, SEO", "5")
        ));

        dto.setFeatures(List.of(
                buildFeature("AI Project Intelligence", "Predictive deadline risk analysis, automatic bottleneck detection, and smart task assignment based on team capacity and skill matching"),
                buildFeature("Offline-First Collaboration", "Work seamlessly without internet using CRDT technology that automatically merges changes when reconnected with zero conflicts"),
                buildFeature("Universal Automation", "No-code workflow builder with 200+ integrations triggering actions across your entire tool stack based on project events"),
                buildFeature("Executive Command Center", "Real-time portfolio view with RAG status, resource utilization, budget tracking, and one-click board reports")
        ));

        ProjectDTO.TestimonialDTO testimonial = new ProjectDTO.TestimonialDTO();
        testimonial.setQuote("We replaced Asana for our 800-person engineering org. The offline sync alone was worth the switch — our teams in low-connectivity regions can finally work as effectively as everyone else.");
        testimonial.setAuthor("James Rodriguez");
        testimonial.setRole("VP Engineering, TechCorp Global");
        dto.setTestimonial(testimonial);

        return dto;
    }

    // ==================================================================================
    // PROJECT 5: AI/ML
    // ==================================================================================
    private ProjectDTO buildAiMlProject() {
        ProjectDTO dto = new ProjectDTO();

        ProjectDTO.MetaDTO meta = new ProjectDTO.MetaDTO();
        meta.setTitle("VisionAI - Computer Vision Quality Control");
        meta.setClient("AutoManufacture Co.");
        meta.setCategory("AI/ML");
        meta.setYear("2024");
        meta.setDuration("9 Months");
        meta.setRole("ML Engineer & Backend Lead");
        meta.setWebsite("https://visionai.auto");
        dto.setMeta(meta);

        dto.setHeroImage("https://placehold.co/1200x600/b45309/ffffff?text=VisionAI+Platform");
        dto.setGallery(List.of(
                "https://placehold.co/800x500/fffbeb/b45309?text=Defect+Detection",
                "https://placehold.co/800x500/fffbeb/b45309?text=Model+Dashboard",
                "https://placehold.co/800x500/fffbeb/b45309?text=Production+Line"
        ));

        ProjectDTO.NarrativeDTO challenge = new ProjectDTO.NarrativeDTO();
        challenge.setDescription("AutoManufacture's quality control relied on 120 human inspectors working 3 shifts across 8 production lines. Defect detection rate was 94% — good, but not good enough. Missed defects were causing $8M/year in warranty claims and two major product recalls in 18 months.");
        challenge.setPoints(List.of(
                "$8M annual warranty claims from missed defects",
                "94% detection rate leaving 6% defects reaching customers",
                "Inspector fatigue causing accuracy drops in night shifts",
                "No traceability — couldn't identify which line caused defects",
                "2 costly product recalls in 18 months"
        ));
        dto.setChallenge(challenge);

        ProjectDTO.NarrativeDTO solution = new ProjectDTO.NarrativeDTO();
        solution.setDescription("Deployed a computer vision system using custom-trained YOLOv8 models processing camera feeds from 64 inspection points in real-time at 120fps, achieving 99.7% defect detection accuracy with full production traceability and predictive maintenance alerts.");
        solution.setPoints(List.of(
                "Custom YOLOv8 models trained on 500K annotated images",
                "64 camera inspection points processing at 120fps",
                "99.7% defect detection accuracy across 47 defect types",
                "Full production traceability with digital twin integration",
                "Predictive maintenance reducing machine downtime by 67%"
        ));
        dto.setSolution(solution);

        dto.setResults(List.of(
                buildResult("Defect Detection", "99.7%", "Up from 94%"),
                buildResult("Warranty Claims", "-$6.2M", "Annual Savings"),
                buildResult("Inspection Speed", "120fps", "Real-Time Processing"),
                buildResult("ROI Achieved", "340%", "Within 12 Months")
        ));

        dto.setTechStack(List.of(
                buildTechStack("Frontend", List.of("React", "Three.js", "WebGL", "Recharts")),
                buildTechStack("Backend", List.of("Python", "FastAPI", "Spring Boot", "gRPC")),
                buildTechStack("Infra", List.of("NVIDIA Jetson", "AWS SageMaker", "InfluxDB", "Kafka")),
                buildTechStack("Tools", List.of("PyTorch", "YOLOv8", "MLflow", "Label Studio"))
        ));

        dto.setProcess(List.of(
                buildProcess("Data Collection & Labeling", "Installing cameras, collecting 500K production images, expert annotation of 47 defect types", "8"),
                buildProcess("Model Development", "YOLOv8 fine-tuning, ensemble modeling, edge optimization for Jetson hardware", "16"),
                buildProcess("Integration & Testing", "PLC integration, MES system connection, shadow mode parallel running with human inspectors", "8"),
                buildProcess("Deployment & Monitoring", "Production rollout by line, model drift monitoring, continuous retraining pipeline", "4")
        ));

        dto.setFeatures(List.of(
                buildFeature("Multi-Defect Detection Engine", "Single-pass detection of 47 defect categories including micro-cracks, surface irregularities, and dimensional tolerances at sub-millimeter precision"),
                buildFeature("Digital Twin Integration", "Real-time 3D visualization of production line with live defect heatmaps and automatic upstream machine correlation"),
                buildFeature("Predictive Maintenance AI", "Vibration, thermal, and visual pattern analysis predicting machine failures 72 hours in advance with 89% accuracy"),
                buildFeature("Continuous Learning Pipeline", "Automated retraining triggered by new defect patterns with human-in-the-loop validation before model promotion to production")
        ));

        ProjectDTO.TestimonialDTO testimonial = new ProjectDTO.TestimonialDTO();
        testimonial.setQuote("We haven't had a single warranty claim related to manufacturing defects in 8 months. The system paid for itself in the first quarter. Our quality director says it's the best investment we've made in a decade.");
        testimonial.setAuthor("Heinrich Mueller");
        testimonial.setRole("COO, AutoManufacture Co.");
        dto.setTestimonial(testimonial);

        return dto;
    }

    // ==================================================================================
    // PROJECT 6: LOGISTICS
    // ==================================================================================
    private ProjectDTO buildLogisticsProject() {
        ProjectDTO dto = new ProjectDTO();

        ProjectDTO.MetaDTO meta = new ProjectDTO.MetaDTO();
        meta.setTitle("RouteIQ - AI-Powered Logistics Optimization");
        meta.setClient("GlobalFreight Ltd.");
        meta.setCategory("Logistics");
        meta.setYear("2023");
        meta.setDuration("7 Months");
        meta.setRole("Backend & ML Engineer");
        meta.setWebsite("https://routeiq.logistics");
        dto.setMeta(meta);

        dto.setHeroImage("https://placehold.co/1200x600/065f46/ffffff?text=RouteIQ+Logistics");
        dto.setGallery(List.of(
                "https://placehold.co/800x500/ecfdf5/065f46?text=Route+Optimizer",
                "https://placehold.co/800x500/ecfdf5/065f46?text=Fleet+Tracker",
                "https://placehold.co/800x500/ecfdf5/065f46?text=Analytics"
        ));

        ProjectDTO.NarrativeDTO challenge = new ProjectDTO.NarrativeDTO();
        challenge.setDescription("GlobalFreight operated 2,400 vehicles across 18 countries using manual route planning that took dispatchers 4 hours each morning. Fuel costs were spiraling, on-time delivery was at 71%, and drivers were burning out from inefficient multi-stop routes.");
        challenge.setPoints(List.of(
                "4-hour manual route planning process each morning",
                "71% on-time delivery rate losing major contracts",
                "Fuel costs 35% above industry benchmark",
                "No dynamic rerouting for traffic or weather events",
                "Driver burnout from inefficient multi-stop routes"
        ));
        dto.setChallenge(challenge);

        ProjectDTO.NarrativeDTO solution = new ProjectDTO.NarrativeDTO();
        solution.setDescription("Built an AI route optimization engine using OR-Tools and reinforcement learning processing 2,400 vehicles and 18,000 daily stops in under 3 minutes, with dynamic real-time rerouting based on live traffic, weather, and vehicle telemetry.");
        solution.setPoints(List.of(
                "OR-Tools + RL optimization solving 18K stops in <3 minutes",
                "Real-time rerouting using Google Maps Platform + weather APIs",
                "Predictive ETAs with 94% accuracy using LSTM models",
                "Driver app with turn-by-turn and automated customer notifications",
                "Fuel optimization reducing consumption by 28%"
        ));
        dto.setSolution(solution);

        dto.setResults(List.of(
                buildResult("On-Time Delivery", "96%", "Up from 71%"),
                buildResult("Fuel Savings", "28%", "$4.2M Annually"),
                buildResult("Planning Time", "3 Minutes", "Down from 4 Hours"),
                buildResult("CO2 Reduction", "31%", "Sustainability Goal Met")
        ));

        dto.setTechStack(List.of(
                buildTechStack("Frontend", List.of("React", "Mapbox GL JS", "TypeScript", "MUI")),
                buildTechStack("Backend", List.of("Python", "FastAPI", "Spring Boot", "OR-Tools")),
                buildTechStack("Infra", List.of("GCP", "BigQuery", "Pub/Sub", "Redis")),
                buildTechStack("Tools", List.of("TensorFlow", "Google Maps API", "HERE Maps", "Grafana"))
        ));

        dto.setProcess(List.of(
                buildProcess("Operations Research", "Vehicle routing problem modeling, constraint definition, historical data analysis", "4"),
                buildProcess("ML Model Development", "ETA prediction LSTM, demand forecasting, reinforcement learning training", "12"),
                buildProcess("Platform & App Build", "Dispatcher web app, driver mobile app, fleet tracking, customer portal", "12"),
                buildProcess("Fleet Integration & Rollout", "Telematics integration, dispatcher training, country-by-country rollout", "8")
        ));

        dto.setFeatures(List.of(
                buildFeature("AI Route Optimizer", "Solves the Vehicle Routing Problem with Time Windows for 2,400 vehicles and 18,000 stops considering load capacity, driver hours, and delivery windows in under 3 minutes"),
                buildFeature("Dynamic Rerouting Engine", "Continuous monitoring of traffic, weather, and road closures with automatic route adjustments pushed to driver apps in real-time"),
                buildFeature("Predictive ETA System", "LSTM neural network trained on 3 years of delivery data providing 94% accurate ETAs with proactive customer SMS notifications"),
                buildFeature("Sustainability Dashboard", "Real-time CO2 tracking per route, driver, and vehicle with carbon offset recommendations and ESG reporting for enterprise clients")
        ));

        ProjectDTO.TestimonialDTO testimonial = new ProjectDTO.TestimonialDTO();
        testimonial.setQuote("RouteIQ didn't just optimize our routes — it transformed our entire operation. We won back two major retail contracts specifically because of our improved delivery reliability. The fuel savings alone fund our next 3 tech investments.");
        testimonial.setAuthor("Sandra Okonkwo");
        testimonial.setRole("VP Operations, GlobalFreight Ltd.");
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