package com.example.ez.intilizer;

import com.example.ez.erpwork.entity.*;
import com.example.ez.erpwork.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ErpDataInitializer implements CommandLineRunner {

    private final ErpServiceRepository serviceRepo;
    private final ErpHeroSectionRepository    heroRepo;
    private final ErpBISectionRepository      biRepo;
    private final ErpCustomerSupportRepository csRepo;
    private final ErpFaqItemRepository        faqRepo;
    private final ErpFeatureTabRepository tabRepo;
    private final ErpFeatureTabItemRepository tabItemRepo;
    private final ErpProductRepository productRepo;
    private final ErpProductFeatureRepository productFeatureRepo;

    @Override
    @Transactional
    public void run(String... args) {
        if (serviceRepo.count() > 0) {
            log.info("✅ ERP data already initialized — skipping.");
            return;
        }
        log.info("🚀 Initializing ERP seed data...");
        seedRentalErp();
        seedFacilityErp();
        seedInventoryErp();
        log.info("✅ ERP seed data initialized successfully.");
    }

    // ══════════════════════════════════════════════════════════════════════
    // RENTAL ERP
    // ══════════════════════════════════════════════════════════════════════
    private void seedRentalErp() {
        ErpService service = serviceRepo.save(
            ErpService.builder()
                .name("Rental ERP")
                .slug("rental")
                .description("The ultimate rental management solution for medium to large rental companies. Streamline all aspects of your rental business for seamless operations and maximum efficiency.")
                .active(true)
                .displayOrder(1)
                .build()
        );

        // ── Hero ──────────────────────────────────────────────────────────
        heroRepo.save(ErpHeroSection.builder()
            .erpService(service)
            .badgeText("EZ CONSTRUCTION® RENTAL ERP")
            .headline("Rental Management Software Built for Growth")
            .description("Ez Construction® Rental ERP gives you complete control over your rental operations — from lease management and equipment tracking to billing, maintenance, and real-time analytics. Run smarter, faster, and more profitably.")
            .ctaButtonText("Get a Demo")
            .ctaButtonLink("/contact")
            .build());

        // ── Business Intelligence ─────────────────────────────────────────
        biRepo.save(ErpBusinessIntelligenceSection.builder()
            .erpService(service)
            .headingPart1("Business Intelligence, to get")
            .headingHighlight("deeper insights")
            .headingPart2("about your rental business")
            .paragraph1("Embrace the only ERP software for rental businesses with advanced real-time Business Intelligence. Harness deep actionable insights to identify areas of improvement and fix them with our tool.")
            .paragraph2("Be it equipment utilization, billing cycles, maintenance costs, or customer retention — keep an eye on the metrics that matter and understand your rental business better.")
            .paragraph3("With our tool and your data, make reports more powerful, dynamic, and fun. Track every asset in real time.")
            .ctaButtonText("Rental Intelligence ->")
            .ctaButtonLink("/business-intelligence")
            .build());

        // ── Customer Support ──────────────────────────────────────────────
        csRepo.save(ErpCustomerSupportSection.builder()
            .erpService(service)
            .title("Rental Customer Support")
            .description("Our world-class rental support team is available around the clock. From onboarding to advanced configuration, our experts ensure your rental operations run without interruption. Get connected to the best expertise in the industry.")
            .ctaButtonText("Rental ERP Support ->")
            .ctaButtonLink("/support")
            .portalBadgeInitial("R")
            .portalBadgeLabel("Rental Portal")
            .build());

        // ── FAQs ──────────────────────────────────────────────────────────
        seedRentalFaqs(service);

        // ── Feature Tabs ──────────────────────────────────────────────────
        seedRentalFeatureTabs(service);

        // ── Products ──────────────────────────────────────────────────────
        seedRentalProducts(service);
    }

    private void seedRentalFaqs(ErpService service) {
        String badge = "EZ CONSTRUCTION RENTAL ERP";
        String title = "Frequently Asked Questions";
        String desc  = "Find answers to common questions about Ez Construction Rental ERP.";

        List<Object[]> faqs = List.of(
                new Object[]{"What is Ez Construction Rental ERP?",
                        "Ez Construction Rental ERP is a comprehensive rental management platform designed specifically for equipment rental, property rental, and facility rental businesses.",
                        "left", 0},
                new Object[]{"How does it manage equipment tracking?",
                        "Our system provides real-time GPS-enabled equipment tracking, utilization reporting, maintenance scheduling, and asset lifecycle management.",
                        "left", 1},
                new Object[]{"Can it handle multiple rental locations?",
                        "Yes, Ez Construction Rental ERP supports multi-branch and multi-location operations with centralized reporting and inter-branch transfers.",
                        "left", 2},
                new Object[]{"How does billing and invoicing work?",
                        "The system automates billing cycles, generates invoices based on rental duration and rates, handles security deposits, and sends automated reminders.",
                        "left", 3},
                new Object[]{"Does it support online customer bookings?",
                        "Yes, our customer self-service portal allows clients to browse available equipment, check pricing, submit rental requests, and track orders online 24/7.",
                        "right", 0},
                new Object[]{"What maintenance features are included?",
                        "Comprehensive maintenance scheduling, work order management, preventive maintenance alerts, service history tracking, and vendor coordination.",
                        "right", 1},
                new Object[]{"Can it integrate with accounting software?",
                        "Ez Construction Rental ERP integrates seamlessly with popular accounting platforms and includes built-in financial management.",
                        "right", 2},
                new Object[]{"Is the system scalable for growth?",
                        "Absolutely. Whether you manage 50 assets or 50,000, our cloud-native infrastructure scales with your business with 99.9% uptime SLA.",
                        "right", 3}
        );

        for (int i = 0; i < faqs.size(); i++) {
            Object[] f = faqs.get(i);
            faqRepo.save(ErpFaqItem.builder()
                    .erpService(service)
                    .question((String) f[0])
                    .answer((String) f[1])
                    .faqColumn((String) f[2])               // ← FIXED
                    .displayOrder((Integer) f[3])
                    .sectionBadgeText(i == 0 ? badge : null)
                    .sectionTitle(i == 0 ? title : null)
                    .sectionDescription(i == 0 ? desc : null)
                    .build());
        }
    }

    private void seedRentalFeatureTabs(ErpService service) {
        // Tab 1: Lease Management
        ErpFeatureTab tab1 = tabRepo.save(ErpFeatureTab.builder()
            .erpService(service)
            .tabId("lease")
            .label("Lease & Contract Management")
            .title("Automate every stage of your rental contract lifecycle")
            .description1("Managing rental agreements manually leads to errors, missed renewals, and revenue leakage. Our lease management module automates every contract touchpoint from creation to termination.")
            .description2("Generate digital contracts, capture e-signatures, set automated renewal alerts, and maintain a complete audit trail — all in one place.")
            .featuresLabel("Features that power lease management:")
            .usesDashboard(true)
            .displayOrder(0)
            .build());
        saveTabItems(tab1, List.of(
            new String[]{"Digital contract generation with custom templates.", "Automated renewal alerts and expiry notifications."},
            new String[]{"E-signature capture and document management.",        "Multi-tier approval workflows for contract amendments."},
            new String[]{"Security deposit tracking and release management.",   "Penalty and late fee automation."},
            new String[]{"Bulk contract updates across multiple tenants.",       "Contract performance analytics and reporting."}
        ));

        // Tab 2: Equipment Tracking
        ErpFeatureTab tab2 = tabRepo.save(ErpFeatureTab.builder()
            .erpService(service)
            .tabId("equipment")
            .label("Equipment Tracking")
            .title("Real-time visibility into every asset in your fleet")
            .description1("Losing track of equipment costs money. Our real-time tracking system gives you complete visibility into the location, status, and utilization of every asset in your inventory.")
            .description2("From heavy machinery to small tools, track utilization rates, schedule maintenance, and optimize deployment to maximize returns on every asset.")
            .featuresLabel("Features that power equipment tracking:")
            .imageUrl(null)
            .imageAlt("Equipment Tracking Dashboard")
            .usesDashboard(true)
            .displayOrder(1)
            .build());
        saveTabItems(tab2, List.of(
            new String[]{"Real-time asset location tracking across all sites.",           "Utilization rate monitoring and optimization alerts."},
            new String[]{"QR code and barcode scanning for fast check-in/check-out.",    "Maintenance scheduling based on usage hours."},
            new String[]{"Asset condition reporting with photo documentation.",           "Depreciation tracking and replacement planning."},
            new String[]{"Inter-branch transfer management with full audit trail.",       "Equipment performance benchmarking and analytics."}
        ));

        // Tab 3: Rental Operations
        ErpFeatureTab tab3 = tabRepo.save(ErpFeatureTab.builder()
            .erpService(service)
            .tabId("operations")
            .label("Rental Operations")
            .title("Streamline your day-to-day rental operations")
            .description1("Rental operations involve dozens of moving parts — bookings, deliveries, returns, inspections, and billing. Our operations module ties them all together seamlessly.")
            .description2("From online booking requests to on-site delivery management, every step is tracked, automated, and visible to all stakeholders in real time.")
            .featuresLabel("Features that power rental operations:")
            .usesDashboard(false)
            .imageAlt("Rental Operations")
            .displayOrder(2)
            .build());
        saveTabItems(tab3, List.of(
            new String[]{"Online booking management with availability calendar.",         "Delivery and collection scheduling with route optimization."},
            new String[]{"On-site inspection checklists with damage documentation.",      "Real-time order status tracking for customers."},
            new String[]{"Off-hire request processing and return management.",            "Automated billing on order completion."},
            new String[]{"Customer self-service portal for bookings and history.",        "Integration with GPS tracking for delivery fleets."}
        ));

        // Tab 4: Maintenance
        ErpFeatureTab tab4 = tabRepo.save(ErpFeatureTab.builder()
            .erpService(service)
            .tabId("maintenance")
            .label("Maintenance Management")
            .title("Keep your fleet in peak condition with smart maintenance")
            .description1("Unplanned equipment downtime is the biggest revenue killer in rental businesses. Our maintenance module shifts you from reactive to proactive maintenance management.")
            .description2("Automated service schedules, vendor management, work order tracking, and maintenance cost analysis ensure your assets are always ready for deployment.")
            .featuresLabel("Features that power maintenance management:")
            .usesDashboard(true)
            .displayOrder(3)
            .build());
        saveTabItems(tab4, List.of(
            new String[]{"Preventive maintenance schedules based on usage and time.",     "Work order creation, assignment, and tracking."},
            new String[]{"Vendor and technician management portal.",                      "Maintenance cost tracking and budget management."},
            new String[]{"Spare parts inventory management and reorder alerts.",          "Service history and warranty tracking per asset."},
            new String[]{"Downtime reporting and availability impact analysis.",          "Mobile app for technicians to update work orders on-site."}
        ));
    }

    private void seedRentalProducts(ErpService service) {
        String badge = "EZ CONSTRUCTION RENTAL ERP";
        String sTitle = "Rental ERP Modules";
        String sDesc  = "Comprehensive rental management modules designed to cover every aspect of your rental business operations.";

        Object[][] products = {
            {"Equipment Rental ERP", "The ultimate solution for equipment rental companies managing large fleets of heavy machinery, tools, and vehicles. Complete visibility from booking to billing.", 0,
             new String[]{"Fleet Management", "Equipment Tracking", "Utilization Analytics", "Maintenance Scheduling", "Customer Portal", "Billing Automation"}},
            {"Property Rental ERP", "Manage residential, commercial, and mixed-use rental properties with full tenant management, lease administration, and financial reporting.", 1,
             new String[]{"Tenant Management", "Lease Administration", "Rent Collection", "Maintenance Requests", "Occupancy Analytics", "Property Accounting"}},
            {"Vehicle Rental ERP", "Designed for car rental, truck leasing, and transportation fleet businesses requiring real-time tracking, booking management, and maintenance scheduling.", 2,
             new String[]{"Fleet Booking Management", "GPS Vehicle Tracking", "Insurance Management", "Driver Management", "Damage Assessment", "Revenue Optimization"}},
            {"Tool & Small Equipment ERP", "Optimized for businesses renting tools, scaffolding, AV equipment, and other small assets with rapid turnover and high transaction volumes.", 3,
             new String[]{"High-Volume Booking", "Barcode Scanning", "Rapid Return Processing", "Loss & Damage Tracking", "Customer Self-Service", "Revenue Analytics"}},
        };

        for (Object[] p : products) {
            ErpProduct product = productRepo.save(ErpProduct.builder()
                .erpService(service)
                .title((String) p[0])
                .description((String) p[1])
                .ctaButtonText("Learn More")
                .ctaButtonLink("/contact")
                .displayOrder((Integer) p[2])
                .sectionBadgeText((Integer) p[2] == 0 ? badge : null)
                .sectionTitle((Integer) p[2] == 0 ? sTitle : null)
                .sectionDescription((Integer) p[2] == 0 ? sDesc : null)
                .build());
            for (int fi = 0; fi < ((String[]) p[3]).length; fi++) {
                productFeatureRepo.save(ErpProductFeature.builder()
                    .erpProduct(product)
                    .featureText(((String[]) p[3])[fi])
                    .displayOrder(fi)
                    .build());
            }
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // FACILITY ERP
    // ══════════════════════════════════════════════════════════════════════
    private void seedFacilityErp() {
        ErpService service = serviceRepo.save(
            ErpService.builder()
                .name("Facility ERP")
                .slug("facility")
                .description("Manage facilities, assets, maintenance, vendors and workforce through one centralized platform designed for operational excellence.")
                .active(true)
                .displayOrder(2)
                .build()
        );

        // ── Hero ──────────────────────────────────────────────────────────
        heroRepo.save(ErpHeroSection.builder()
            .erpService(service)
            .badgeText("EZ CONSTRUCTION® FACILITY ERP")
            .headline("Facility Management Software for Operational Excellence")
            .description("Ez Construction® Facility ERP centralizes all your facility operations — from asset management and maintenance to vendor coordination and compliance — into one powerful platform. Reduce costs, improve efficiency, and deliver exceptional occupant experiences.")
            .ctaButtonText("Get a Demo")
            .ctaButtonLink("/contact")
            .build());

        // ── Business Intelligence ─────────────────────────────────────────
        biRepo.save(ErpBusinessIntelligenceSection.builder()
            .erpService(service)
            .headingPart1("Facility Intelligence, to get")
            .headingHighlight("complete operational control")
            .headingPart2("across all your facilities")
            .paragraph1("Leverage advanced analytics to gain complete visibility into facility performance, maintenance costs, energy consumption, and vendor SLA compliance — all in real time.")
            .paragraph2("Our BI dashboards help facility managers make data-driven decisions on maintenance budgets, capital expenditure planning, and resource allocation.")
            .paragraph3("Transform raw operational data into actionable insights that drive down costs and improve service delivery across all facilities.")
            .ctaButtonText("Facility Intelligence ->")
            .ctaButtonLink("/business-intelligence")
            .build());

        // ── Customer Support ──────────────────────────────────────────────
        csRepo.save(ErpCustomerSupportSection.builder()
            .erpService(service)
            .title("Facility Management Support")
            .description("Our dedicated facility management support team provides 24/7 assistance for all your operational needs. From work order management to compliance queries, our experts ensure your facilities run smoothly without interruption.")
            .ctaButtonText("Facility ERP Support ->")
            .ctaButtonLink("/support")
            .portalBadgeInitial("F")
            .portalBadgeLabel("Facility Portal")
            .build());

        // ── FAQs ──────────────────────────────────────────────────────────
        seedFacilityFaqs(service);

        // ── Feature Tabs ──────────────────────────────────────────────────
        seedFacilityFeatureTabs(service);

        // ── Products ──────────────────────────────────────────────────────
        seedFacilityProducts(service);
    }

    private void seedFacilityFaqs(ErpService service) {
        String badge = "EZ CONSTRUCTION FACILITY ERP";
        String title = "Frequently Asked Questions";
        String desc  = "Find answers to common questions about Ez Construction Facility ERP.";

        List<Object[]> faqs = List.of(
                new Object[]{"What is Ez Construction Facility ERP?",
                        "Ez Construction Facility ERP is a comprehensive facility management platform that integrates asset management, preventive maintenance, vendor management, compliance tracking, and occupant services.",
                        "left", 0},
                new Object[]{"How does it handle preventive maintenance?",
                        "The system creates automated maintenance schedules based on asset age, usage, and manufacturer recommendations. It generates work orders, assigns technicians, and maintains full service history.",
                        "left", 1},
                new Object[]{"Can it manage multiple facilities?",
                        "Yes, our multi-facility management gives you centralized visibility and control across all locations with site-specific reporting and consolidated financial views.",
                        "left", 2},
                new Object[]{"How does vendor management work?",
                        "The platform manages your entire vendor ecosystem from onboarding and contract management to work order assignment, SLA monitoring, and performance scoring.",
                        "left", 3},
                new Object[]{"Does it support compliance management?",
                        "Yes, Ez Construction Facility ERP tracks regulatory requirements, manages inspection schedules, stores compliance documents, and generates compliance audit reports.",
                        "right", 0},
                new Object[]{"Can occupants raise service requests?",
                        "Our occupant self-service portal allows building occupants to raise maintenance requests, track status in real time, and provide feedback from any device.",
                        "right", 1},
                new Object[]{"How is energy management handled?",
                        "The platform integrates with energy monitoring systems to track consumption, identify inefficiencies, set benchmarks, and generate optimization recommendations.",
                        "right", 2},
                new Object[]{"What reporting capabilities are available?",
                        "Comprehensive reporting includes maintenance cost analysis, asset lifecycle reports, vendor performance scorecards, energy trends, and executive KPI dashboards.",
                        "right", 3}
        );

        for (int i = 0; i < faqs.size(); i++) {
            Object[] f = faqs.get(i);
            faqRepo.save(ErpFaqItem.builder()
                    .erpService(service)
                    .question((String) f[0])
                    .answer((String) f[1])
                    .faqColumn((String) f[2])               // ← FIXED
                    .displayOrder((Integer) f[3])
                    .sectionBadgeText(i == 0 ? badge : null)
                    .sectionTitle(i == 0 ? title : null)
                    .sectionDescription(i == 0 ? desc : null)
                    .build());
        }
    }

    private void seedFacilityFeatureTabs(ErpService service) {
        // Tab 1: Asset Management
        ErpFeatureTab tab1 = tabRepo.save(ErpFeatureTab.builder()
            .erpService(service)
            .tabId("assets")
            .label("Asset Management")
            .title("Complete lifecycle management for every facility asset")
            .description1("Track every asset from acquisition to disposal with complete visibility into location, condition, maintenance history, and financial value. Never lose an asset again.")
            .description2("Our asset management module integrates with maintenance, finance, and procurement to give you a 360-degree view of your entire asset portfolio.")
            .featuresLabel("Features that power asset management:")
            .usesDashboard(true)
            .displayOrder(0)
            .build());
        saveTabItems(tab1, List.of(
            new String[]{"Complete asset registry with QR code tagging.",          "Asset location tracking across all facility zones."},
            new String[]{"Depreciation calculation and book value tracking.",       "Asset condition assessments with photo documentation."},
            new String[]{"Capital expenditure planning and approval workflows.",    "Asset warranty and insurance management."},
            new String[]{"Asset transfer between departments and locations.",        "Asset disposal and write-off management."}
        ));

        // Tab 2: Maintenance Scheduling
        ErpFeatureTab tab2 = tabRepo.save(ErpFeatureTab.builder()
            .erpService(service)
            .tabId("maintenance")
            .label("Maintenance Scheduling")
            .title("Shift from reactive to proactive maintenance management")
            .description1("Unplanned breakdowns are expensive. Our intelligent maintenance scheduling predicts failures before they happen and keeps your facilities running at peak efficiency.")
            .description2("Automated PPM schedules, work order management, technician dispatching, and real-time status tracking ensure nothing falls through the cracks.")
            .featuresLabel("Features that power maintenance scheduling:")
            .usesDashboard(true)
            .displayOrder(1)
            .build());
        saveTabItems(tab2, List.of(
            new String[]{"Planned preventive maintenance (PPM) scheduling.",         "Reactive work order creation and priority management."},
            new String[]{"Technician skill matching and workload balancing.",         "Mobile work order management for field technicians."},
            new String[]{"Spare parts inventory and consumption tracking.",           "Maintenance cost tracking by asset, location, and department."},
            new String[]{"SLA monitoring and breach alerts.",                         "Contractor and vendor work order assignment and tracking."}
        ));

        // Tab 3: Vendor Management
        ErpFeatureTab tab3 = tabRepo.save(ErpFeatureTab.builder()
            .erpService(service)
            .tabId("vendors")
            .label("Vendor Management")
            .title("Manage your entire vendor ecosystem from one platform")
            .description1("Vendor relationships are complex. Our vendor management module brings all vendor interactions — contracts, work orders, invoices, and performance — into one unified view.")
            .description2("Score vendor performance against SLAs, manage contracts, process invoices, and make data-driven vendor selection decisions.")
            .featuresLabel("Features that power vendor management:")
            .usesDashboard(false)
            .imageAlt("Vendor Management")
            .displayOrder(2)
            .build());
        saveTabItems(tab3, List.of(
            new String[]{"Vendor onboarding with document verification.",             "Contract management with renewal alerts."},
            new String[]{"Work order assignment and progress tracking.",              "SLA monitoring with automated breach notifications."},
            new String[]{"Invoice processing and payment approval workflows.",        "Vendor performance scoring and benchmarking."},
            new String[]{"Preferred vendor lists and rate card management.",          "Vendor portal for real-time work order updates."}
        ));

        // Tab 4: Compliance
        ErpFeatureTab tab4 = tabRepo.save(ErpFeatureTab.builder()
            .erpService(service)
            .tabId("compliance")
            .label("Compliance & Safety")
            .title("Stay compliant and safe across all your facilities")
            .description1("Regulatory compliance is non-negotiable. Our compliance module tracks all statutory requirements, manages inspection schedules, and ensures your facilities always meet the required standards.")
            .description2("From fire safety certificates to environmental compliance, every requirement is tracked with automated alerts so nothing expires unnoticed.")
            .featuresLabel("Features that power compliance management:")
            .usesDashboard(true)
            .displayOrder(3)
            .build());
        saveTabItems(tab4, List.of(
            new String[]{"Compliance requirement tracking by facility and asset type.", "Inspection scheduling and checklist management."},
            new String[]{"Certificate and permit storage with expiry alerts.",          "Safety incident reporting and investigation tracking."},
            new String[]{"Regulatory audit trail and documentation management.",        "Compliance dashboard with status RAG ratings."},
            new String[]{"Risk assessment management and action tracking.",              "Automated compliance reports for regulatory submissions."}
        ));
    }

    private void seedFacilityProducts(ErpService service) {
        String badge = "EZ CONSTRUCTION FACILITY ERP";
        String sTitle = "Facility ERP Modules";
        String sDesc  = "Comprehensive facility management modules for every type of building and operational requirement.";

        Object[][] products = {
            {"Commercial Facility ERP", "Complete facility management for office buildings, retail spaces, and commercial complexes with full maintenance, compliance, and tenant management.", 0,
             new String[]{"Asset Management", "PPM Scheduling", "Tenant Services", "Energy Monitoring", "Compliance Tracking", "Vendor Management"}},
            {"Industrial Facility ERP", "Designed for factories, warehouses, and industrial facilities requiring heavy equipment maintenance, safety compliance, and operational continuity.", 1,
             new String[]{"Heavy Equipment Maintenance", "Safety Management", "Production Line Support", "Environmental Compliance", "Work Order Management", "Shift Management"}},
            {"Healthcare Facility ERP", "Specialized facility management for hospitals and clinics with biomedical equipment tracking, infection control protocols, and regulatory compliance.", 2,
             new String[]{"Biomedical Equipment", "Infection Control", "Joint Commission Compliance", "Emergency Maintenance", "Vendor Credentialing", "Patient Area Management"}},
            {"Educational Facility ERP", "Purpose-built for schools, colleges, and universities managing campus infrastructure, event spaces, and student facility services.", 3,
             new String[]{"Campus Management", "Event Space Booking", "Student Service Requests", "IT Infrastructure", "Energy Management", "Safety & Security"}},
        };

        for (Object[] p : products) {
            ErpProduct product = productRepo.save(ErpProduct.builder()
                .erpService(service)
                .title((String) p[0])
                .description((String) p[1])
                .ctaButtonText("Learn More")
                .ctaButtonLink("/contact")
                .displayOrder((Integer) p[2])
                .sectionBadgeText((Integer) p[2] == 0 ? badge : null)
                .sectionTitle((Integer) p[2] == 0 ? sTitle : null)
                .sectionDescription((Integer) p[2] == 0 ? sDesc : null)
                .build());
            for (int fi = 0; fi < ((String[]) p[3]).length; fi++) {
                productFeatureRepo.save(ErpProductFeature.builder()
                    .erpProduct(product)
                    .featureText(((String[]) p[3])[fi])
                    .displayOrder(fi)
                    .build());
            }
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // INVENTORY ERP
    // ══════════════════════════════════════════════════════════════════════
    private void seedInventoryErp() {
        ErpService service = serviceRepo.save(
            ErpService.builder()
                .name("Inventory ERP")
                .slug("inventory")
                .description("Real-time inventory control across all your locations. Never face stockouts or overstock situations with intelligent automated inventory management.")
                .active(true)
                .displayOrder(3)
                .build()
        );

        // ── Hero ──────────────────────────────────────────────────────────
        heroRepo.save(ErpHeroSection.builder()
            .erpService(service)
            .badgeText("EZ CONSTRUCTION® INVENTORY ERP")
            .headline("Inventory Management That Eliminates Stockouts Forever")
            .description("Ez Construction® Inventory ERP gives you complete real-time visibility of your stock across all warehouses and locations. Automated reorder points, intelligent demand forecasting, and barcode scanning eliminate stockouts and reduce carrying costs.")
            .ctaButtonText("Get a Demo")
            .ctaButtonLink("/contact")
            .build());

        // ── Business Intelligence ─────────────────────────────────────────
        biRepo.save(ErpBusinessIntelligenceSection.builder()
            .erpService(service)
            .headingPart1("Inventory Intelligence, to get")
            .headingHighlight("complete stock visibility")
            .headingPart2("across all your warehouses")
            .paragraph1("Leverage advanced inventory analytics to understand demand patterns, optimize stock levels, reduce carrying costs, and eliminate both stockouts and overstock situations.")
            .paragraph2("Our BI dashboards provide real-time insights into stock turnover rates, slow-moving inventory, supplier performance, and warehouse efficiency metrics.")
            .paragraph3("Make procurement decisions backed by data — not gut feeling. Our forecasting engine analyzes historical patterns to predict future demand with precision.")
            .ctaButtonText("Inventory Intelligence ->")
            .ctaButtonLink("/business-intelligence")
            .build());

        // ── Customer Support ──────────────────────────────────────────────
        csRepo.save(ErpCustomerSupportSection.builder()
            .erpService(service)
            .title("Inventory ERP Support")
            .description("Our inventory management support team provides rapid assistance for all operational queries. From warehouse setup and barcode configuration to advanced demand forecasting, our specialists are here to optimize your inventory operations.")
            .ctaButtonText("Inventory ERP Support ->")
            .ctaButtonLink("/support")
            .portalBadgeInitial("I")
            .portalBadgeLabel("Inventory Portal")
            .build());

        // ── FAQs ──────────────────────────────────────────────────────────
        seedInventoryFaqs(service);

        // ── Feature Tabs ──────────────────────────────────────────────────
        seedInventoryFeatureTabs(service);

        // ── Products ──────────────────────────────────────────────────────
        seedInventoryProducts(service);
    }

    private void seedInventoryFaqs(ErpService service) {
        String badge = "EZ CONSTRUCTION INVENTORY ERP";
        String title = "Frequently Asked Questions";
        String desc  = "Find answers to common questions about Ez Construction Inventory ERP.";

        List<Object[]> faqs = List.of(
                new Object[]{"What is Ez Construction Inventory ERP?",
                        "Ez Construction Inventory ERP is a comprehensive stock management platform with real-time visibility across all warehouse locations, automated reordering, barcode scanning, and demand forecasting.",
                        "left", 0},
                new Object[]{"How does real-time stock tracking work?",
                        "Every stock movement is captured in real time through barcode scanning, RFID, or manual entry. Dashboards update instantly giving you live stock levels at every location.",
                        "left", 1},
                new Object[]{"How does automated reordering work?",
                        "You set minimum stock levels and reorder quantities per item. When stock falls below the threshold, the system automatically generates purchase requisitions — eliminating stockouts.",
                        "left", 2},
                new Object[]{"Can it handle multiple warehouses?",
                        "Yes, our multi-warehouse management gives you centralized control with location-specific tracking, inter-warehouse transfers, and consolidated inventory reporting.",
                        "left", 3},
                new Object[]{"Does it support batch and serial tracking?",
                        "Full batch and serial number tracking including expiry date management, FIFO/FEFO stock rotation, traceability reports, and recall management.",
                        "right", 0},
                new Object[]{"How does demand forecasting work?",
                        "Our AI-powered forecasting analyzes historical sales data, seasonal patterns, and lead times to predict future demand — helping you optimize stock levels.",
                        "right", 1},
                new Object[]{"Can it integrate with procurement and sales?",
                        "Yes, Inventory ERP integrates natively with Procurement and Sales modules, automatically updating stock on purchase receipts and sales orders.",
                        "right", 2},
                new Object[]{"What valuation methods are supported?",
                        "We support FIFO, LIFO, FEFO, weighted average, and standard cost valuation with automatic inventory valuation reports and cost-of-goods-sold calculations.",
                        "right", 3}
        );

        for (int i = 0; i < faqs.size(); i++) {
            Object[] f = faqs.get(i);
            faqRepo.save(ErpFaqItem.builder()
                    .erpService(service)
                    .question((String) f[0])
                    .answer((String) f[1])
                    .faqColumn((String) f[2])               // ← FIXED
                    .displayOrder((Integer) f[3])
                    .sectionBadgeText(i == 0 ? badge : null)
                    .sectionTitle(i == 0 ? title : null)
                    .sectionDescription(i == 0 ? desc : null)
                    .build());
        }
    }

    private void seedInventoryFeatureTabs(ErpService service) {
        // Tab 1: Stock Control
        ErpFeatureTab tab1 = tabRepo.save(ErpFeatureTab.builder()
            .erpService(service)
            .tabId("stock-control")
            .label("Real-Time Stock Control")
            .title("Complete visibility of every item across all your locations")
            .description1("Stop guessing what you have in stock. Our real-time stock control gives you live inventory levels across every warehouse, bin, and location — updated instantly on every transaction.")
            .description2("From raw materials to finished goods, every item is tracked with full traceability — giving you confidence in every number on your inventory reports.")
            .featuresLabel("Features that power real-time stock control:")
            .usesDashboard(true)
            .displayOrder(0)
            .build());
        saveTabItems(tab1, List.of(
            new String[]{"Live stock levels across all warehouses and locations.",    "Instant updates on every stock movement and transaction."},
            new String[]{"Bin and rack location management for precise tracking.",    "Stock reservation for confirmed sales orders."},
            new String[]{"Negative stock prevention and alert management.",           "Cycle counting and physical stock audit tools."},
            new String[]{"Stock adjustment with reason code tracking.",               "Real-time dashboard with stock value and movement KPIs."}
        ));

        // Tab 2: Demand Forecasting
        ErpFeatureTab tab2 = tabRepo.save(ErpFeatureTab.builder()
            .erpService(service)
            .tabId("forecasting")
            .label("Demand Forecasting")
            .title("Predict demand accurately and never run out of stock")
            .description1("Demand forecasting is the difference between profitable operations and costly stockouts or write-offs. Our AI-powered forecasting engine analyzes your unique demand patterns.")
            .description2("Set safety stock levels intelligently, plan procurement proactively, and reduce both stockouts and excess inventory with data-driven demand planning.")
            .featuresLabel("Features that power demand forecasting:")
            .usesDashboard(true)
            .displayOrder(1)
            .build());
        saveTabItems(tab2, List.of(
            new String[]{"AI-powered demand prediction using historical data.",          "Seasonal adjustment and trend analysis."},
            new String[]{"Safety stock optimization based on service level targets.",    "Lead time analysis and supplier reliability scoring."},
            new String[]{"ABC/XYZ analysis for inventory classification.",              "Slow-moving and obsolete stock identification."},
            new String[]{"Automated purchase requisition generation.",                   "Forecast accuracy monitoring and improvement tools."}
        ));

        // Tab 3: Warehouse Management
        ErpFeatureTab tab3 = tabRepo.save(ErpFeatureTab.builder()
            .erpService(service)
            .tabId("warehouse")
            .label("Warehouse Management")
            .title("Optimize your warehouse for speed and accuracy")
            .description1("Warehouse efficiency directly impacts your ability to fulfill orders on time. Our warehouse management module optimizes putaway, picking, packing, and dispatch for maximum throughput.")
            .description2("From receiving dock to dispatch bay, every movement is guided, tracked, and optimized — reducing errors and improving order fulfillment speed.")
            .featuresLabel("Features that power warehouse management:")
            .usesDashboard(false)
            .imageAlt("Warehouse Management")
            .displayOrder(2)
            .build());
        saveTabItems(tab3, List.of(
            new String[]{"Optimized putaway rules for efficient space utilization.",     "Directed picking with route optimization."},
            new String[]{"Barcode and RFID scanning for error-free operations.",         "Batch and wave picking for high-volume operations."},
            new String[]{"Goods receipt and quality inspection management.",             "Packing and dispatch management with label printing."},
            new String[]{"Warehouse layout mapping with bin location management.",       "Cross-docking and transit stock management."}
        ));

        // Tab 4: Procurement Integration
        ErpFeatureTab tab4 = tabRepo.save(ErpFeatureTab.builder()
            .erpService(service)
            .tabId("procurement")
            .label("Procurement Integration")
            .title("Connect inventory seamlessly with your procurement process")
            .description1("Inventory and procurement should work as one. Our native procurement integration automatically triggers purchase orders when stock hits reorder points and updates inventory on every receipt.")
            .description2("End the manual reconciliation between purchasing and warehousing. Every PO, receipt, and invoice updates inventory in real time — giving you accurate stock and cost data always.")
            .featuresLabel("Features that power procurement integration:")
            .usesDashboard(true)
            .displayOrder(3)
            .build());
        saveTabItems(tab4, List.of(
            new String[]{"Automatic PO generation on reorder point breach.",             "Three-way matching: PO, GRN, and invoice."},
            new String[]{"Supplier catalog management with price comparison.",           "Purchase order approval workflows and authorization limits."},
            new String[]{"Goods receipt processing with quality inspection.",            "Supplier performance tracking and scorecard."},
            new String[]{"Landed cost calculation and inventory valuation update.",      "Return-to-supplier management with debit note generation."}
        ));
    }

    private void seedInventoryProducts(ErpService service) {
        String badge = "EZ CONSTRUCTION INVENTORY ERP";
        String sTitle = "Inventory ERP Modules";
        String sDesc  = "Specialized inventory management modules for every industry and operational complexity level.";

        Object[][] products = {
            {"Manufacturing Inventory ERP", "Purpose-built for manufacturing businesses managing raw materials, WIP, and finished goods with BOM management, production planning, and shop floor control.", 0,
             new String[]{"BOM Management", "Production Planning", "Raw Material Tracking", "WIP Management", "Quality Control", "Batch Traceability"}},
            {"Distribution Inventory ERP", "Optimized for distributors and wholesalers managing high-volume SKUs across multiple warehouses with rapid order fulfillment and multi-channel sales.", 1,
             new String[]{"Multi-Warehouse Management", "High-Volume Order Processing", "Pick & Pack Optimization", "Cross-Docking", "Channel Management", "Demand Forecasting"}},
            {"Retail Inventory ERP", "Designed for retail businesses managing store stock, online inventory, and supply chain with real-time omnichannel visibility and replenishment automation.", 2,
             new String[]{"Omnichannel Stock View", "Store Replenishment", "POS Integration", "Shrinkage Management", "Seasonal Planning", "Supplier Management"}},
            {"Construction Inventory ERP", "Specialized for construction companies managing site materials, equipment spares, and consumables across multiple project sites with cost allocation.", 3,
             new String[]{"Site Material Management", "Project Cost Allocation", "Equipment Spare Parts", "Subcontractor Materials", "Wastage Tracking", "Budget vs Actual"}},
        };

        for (Object[] p : products) {
            ErpProduct product = productRepo.save(ErpProduct.builder()
                .erpService(service)
                .title((String) p[0])
                .description((String) p[1])
                .ctaButtonText("Learn More")
                .ctaButtonLink("/contact")
                .displayOrder((Integer) p[2])
                .sectionBadgeText((Integer) p[2] == 0 ? badge : null)
                .sectionTitle((Integer) p[2] == 0 ? sTitle : null)
                .sectionDescription((Integer) p[2] == 0 ? sDesc : null)
                .build());
            for (int fi = 0; fi < ((String[]) p[3]).length; fi++) {
                productFeatureRepo.save(ErpProductFeature.builder()
                    .erpProduct(product)
                    .featureText(((String[]) p[3])[fi])
                    .displayOrder(fi)
                    .build());
            }
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // HELPER
    // ══════════════════════════════════════════════════════════════════════
    private void saveTabItems(ErpFeatureTab tab, List<String[]> pairs) {
        for (int i = 0; i < pairs.size(); i++) {
            tabItemRepo.save(ErpFeatureTabItem.builder()
                .featureTab(tab)
                .leftText(pairs.get(i)[0])
                .rightText(pairs.get(i)[1])
                .displayOrder(i)
                .build());
        }
    }
}