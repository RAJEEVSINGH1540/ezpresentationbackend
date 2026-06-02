package com.example.ez.intilizer;

import com.example.ez.services.entity.servicepage.*;
import com.example.ez.services.repository.servicepage.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CmsDataInitializer implements CommandLineRunner {

    private final HeroSectionRepository heroRepo;
    private final TrustedClientsSectionRepository trustedRepo;
    private final DashboardShowcaseSectionRepository dashboardRepo;
    private final WhyChooseUsSectionRepository whyRepo;
    private final IndustriesSectionRepository industriesRepo;
    private final BenefitsSectionRepository benefitsRepo;
    private final TestimonialsSectionRepository testimonialsRepo;
    private final PricingSectionRepository pricingRepo;
    private final FinalCtaSectionRepository finalCtaRepo;

    @Override
    public void run(String... args) {
        initHero();
        initTrustedClients();
        initDashboardShowcase();
        initWhyChooseUs();
        initIndustries();
        initBenefits();
        initTestimonials();
        initPricing();
        initFinalCta();
        log.info("✅ CMS Data Initializer completed successfully!");
    }

    // ════════════════════════════════════════════════════════════════
    //  1. HERO SECTION
    // ════════════════════════════════════════════════════════════════
    private void initHero() {
        if (heroRepo.count() == 0) {
            heroRepo.save(HeroSection.builder()
                    .badge("Enterprise ERP & HRMS Platform — Trusted by 500+ Companies")
                    .heading("Unified ERP & HRMS Platform")
                    .headingHighlight("For Modern Enterprises")
                    .subheading("Streamline operations, automate payroll, manage talent, and gain real-time insights — all from a single, intelligent enterprise platform.")
                    .ctaPrimaryText("Book a Demo")
                    .ctaSecondaryText("Explore Solutions")
                    .badgeColor("#1a56e8")
                    .primaryColor("#1a56e8")
                    .imageUrl("")
                    .isActive(true)
                    .build());
            log.info("✅ Hero section initialized");
        }
    }

    // ════════════════════════════════════════════════════════════════
    //  2. TRUSTED CLIENTS SECTION
    // ════════════════════════════════════════════════════════════════
    private void initTrustedClients() {
        if (trustedRepo.count() == 0) {
            String statsJson = """
                    [
                      {"value": "500+", "label": "Enterprise Clients"},
                      {"value": "50K+", "label": "Employees Managed"},
                      {"value": "99.9%", "label": "Uptime SLA"},
                      {"value": "$2.4B+", "label": "Payroll Processed"}
                    ]
                    """;

            String logosJson = """
                    [
                      {"name": "Microsoft", "imageUrl": ""},
                      {"name": "Deloitte", "imageUrl": ""},
                      {"name": "Accenture", "imageUrl": ""},
                      {"name": "PwC", "imageUrl": ""},
                      {"name": "KPMG", "imageUrl": ""},
                      {"name": "Oracle", "imageUrl": ""},
                      {"name": "SAP", "imageUrl": ""},
                      {"name": "IBM", "imageUrl": ""},
                      {"name": "Infosys", "imageUrl": ""},
                      {"name": "Wipro", "imageUrl": ""}
                    ]
                    """;

            trustedRepo.save(TrustedClientsSection.builder()
                    .sectionLabel("Trusted by leading enterprises worldwide")
                    .statsJson(statsJson.trim())
                    .logosJson(logosJson.trim())
                    .primaryColor("#1a56e8")
                    .isActive(true)
                    .build());
            log.info("✅ Trusted Clients section initialized");
        }
    }

    // ════════════════════════════════════════════════════════════════
    //  3. DASHBOARD SHOWCASE SECTION
    // ════════════════════════════════════════════════════════════════
    private void initDashboardShowcase() {
        if (dashboardRepo.count() == 0) {
            String tabsJson = """
                    {
                      "ERP Overview": {
                        "headline": "Real-Time ERP Intelligence at Your Fingertips",
                        "desc": "Monitor every business function from a single command center. Finance, operations, procurement, and executive KPIs — all unified.",
                        "metrics": [
                          {"label": "Revenue MTD", "value": "$2.4M", "up": true},
                          {"label": "Operating Cost", "value": "$620K", "up": false},
                          {"label": "Net Margin", "value": "28.4%", "up": true}
                        ]
                      },
                      "HRMS Analytics": {
                        "headline": "Workforce Intelligence That Drives Decisions",
                        "desc": "Understand your people with deep analytics — attrition prediction, performance trends, hiring funnels, and department-level insights.",
                        "metrics": [
                          {"label": "Active Employees", "value": "1,284", "up": true},
                          {"label": "Attrition Rate", "value": "4.2%", "up": false},
                          {"label": "Avg Performance", "value": "87/100", "up": true}
                        ]
                      },
                      "Payroll Reports": {
                        "headline": "Payroll Automation That Never Misses a Beat",
                        "desc": "Automated salary processing, statutory compliance, tax calculations, and multi-currency payroll — all on schedule.",
                        "metrics": [
                          {"label": "Processed MTD", "value": "$840K", "up": true},
                          {"label": "Accuracy Rate", "value": "99.98%", "up": true},
                          {"label": "On-Time", "value": "100%", "up": true}
                        ]
                      },
                      "Mobile App": {
                        "headline": "Enterprise Power, Mobile Simplicity",
                        "desc": "Give your workforce access to leave management, approvals, payslips, and real-time notifications — from any device.",
                        "metrics": [
                          {"label": "Active Users", "value": "1,200+", "up": true},
                          {"label": "Daily Actions", "value": "8,400", "up": true},
                          {"label": "App Rating", "value": "4.8★", "up": true}
                        ]
                      }
                    }
                    """;

            dashboardRepo.save(DashboardShowcaseSection.builder()
                    .badge("Product Preview")
                    .heading("See the Platform in Action")
                    .subheading("Explore the depth and elegance of our enterprise modules through live dashboard previews.")
                    .ctaText("Schedule Live Demo")
                    .tabsJson(tabsJson.trim())
                    .primaryColor("#1a56e8")
                    .isActive(true)
                    .build());
            log.info("✅ Dashboard Showcase section initialized");
        }
    }

    // ════════════════════════════════════════════════════════════════
    //  4. WHY CHOOSE US SECTION
    // ════════════════════════════════════════════════════════════════
    private void initWhyChooseUs() {
        if (whyRepo.count() == 0) {
            String featuresJson = """
                    [
                      {
                        "icon": "🛡️",
                        "title": "Enterprise Security",
                        "desc": "SOC 2 Type II certified with AES-256 encryption, MFA, role-based access, and complete audit trails.",
                        "color": "#1a56e8",
                        "large": true
                      },
                      {
                        "icon": "⚡",
                        "title": "Real-Time Analytics",
                        "desc": "Live dashboards and instant reporting across all modules.",
                        "color": "#7c3aed",
                        "large": false
                      },
                      {
                        "icon": "🧠",
                        "title": "AI-Powered Insights",
                        "desc": "Predictive analytics, smart recommendations, and automated anomaly detection across your business data.",
                        "color": "#059669",
                        "large": true
                      },
                      {
                        "icon": "☁️",
                        "title": "Cloud Infrastructure",
                        "desc": "Multi-region cloud with 99.9% uptime SLA.",
                        "color": "#0891b2",
                        "large": false
                      },
                      {
                        "icon": "🏢",
                        "title": "Multi-Branch Management",
                        "desc": "Manage unlimited branches, entities, and subsidiaries from a single admin console.",
                        "color": "#dc2626",
                        "large": false
                      },
                      {
                        "icon": "🚀",
                        "title": "Fast Deployment",
                        "desc": "Go live in 14 days with guided implementation.",
                        "color": "#d97706",
                        "large": false
                      },
                      {
                        "icon": "💬",
                        "title": "Dedicated Support",
                        "desc": "Named account managers, 24/7 enterprise support, and SLA-backed response times for critical issues.",
                        "color": "#1a56e8",
                        "large": true
                      }
                    ]
                    """;

            whyRepo.save(WhyChooseUsSection.builder()
                    .badge("Why Enterprises Choose Us")
                    .heading("Built for the Demands of")
                    .headingHighlight("Modern Enterprise")
                    .subheading("Infrastructure, security, and intelligence at the scale your organization requires.")
                    .featuresJson(featuresJson.trim())
                    .primaryColor("#1a56e8")
                    .isActive(true)
                    .build());
            log.info("✅ Why Choose Us section initialized");
        }
    }

    // ════════════════════════════════════════════════════════════════
    //  5. INDUSTRIES SECTION
    // ════════════════════════════════════════════════════════════════
    private void initIndustries() {
        if (industriesRepo.count() == 0) {
            String industriesJson = """
                    [
                      {"name": "Construction", "desc": "Project costing, contractor management, and site-level analytics.", "icon": "🏗️", "color": "#d97706"},
                      {"name": "Manufacturing", "desc": "Production scheduling, quality control, and supply chain visibility.", "icon": "🏭", "color": "#1a56e8"},
                      {"name": "Healthcare", "desc": "Staff credentialing, compliance tracking, and patient operations.", "icon": "🏥", "color": "#dc2626"},
                      {"name": "Retail", "desc": "POS integration, inventory, and multi-store HR management.", "icon": "🛒", "color": "#7c3aed"},
                      {"name": "Education", "desc": "Faculty management, student administration, and campus operations.", "icon": "🎓", "color": "#059669"},
                      {"name": "Logistics", "desc": "Fleet management, route optimization, and delivery workforce tools.", "icon": "🚛", "color": "#0891b2"},
                      {"name": "Hospitality", "desc": "Multi-property HR, guest-facing operations, and revenue reporting.", "icon": "🏨", "color": "#ec4899"}
                    ]
                    """;

            industriesRepo.save(IndustriesSection.builder()
                    .badge("Industry Coverage")
                    .heading("Designed for Every")
                    .headingHighlight("Industry Vertical")
                    .subheading("Pre-configured workflows, compliance modules, and industry templates out of the box.")
                    .industriesJson(industriesJson.trim())
                    .ctaCardTitle("Your Industry?")
                    .ctaCardDesc("We build custom ERP solutions for any sector.")
                    .primaryColor("#1a56e8")
                    .isActive(true)
                    .build());
            log.info("✅ Industries section initialized");
        }
    }

    // ════════════════════════════════════════════════════════════════
    //  6. BENEFITS SECTION
    // ════════════════════════════════════════════════════════════════
    private void initBenefits() {
        if (benefitsRepo.count() == 0) {
            String benefitsJson = """
                    [
                      {
                        "tag": "Operations",
                        "title": "Automate Your Entire Business Operations",
                        "desc": "Replace manual processes with intelligent automation across procurement, approvals, inventory, and finance. Reduce operational overhead by up to 60% while improving accuracy.",
                        "points": [
                          "Smart approval workflows",
                          "Automated vendor communications",
                          "Rules-based process triggers",
                          "Cross-department orchestration"
                        ],
                        "color": "#1a56e8",
                        "bars": [
                          {"label": "Automation Rate", "value": "94%", "w": 94},
                          {"label": "Manual Tasks Reduced", "value": "60%", "w": 60},
                          {"label": "Process Accuracy", "value": "99.8%", "w": 99}
                        ]
                      },
                      {
                        "tag": "HR & Payroll",
                        "title": "Simplify HR Management at Enterprise Scale",
                        "desc": "From hire to retire — manage the complete employee lifecycle with intelligent workflows, automated compliance, and real-time workforce analytics.",
                        "points": [
                          "Onboarding & offboarding automation",
                          "Performance management cycles",
                          "Leave & attendance tracking",
                          "Statutory compliance engine"
                        ],
                        "color": "#7c3aed",
                        "bars": [
                          {"label": "Onboarding Speed", "value": "3x faster", "w": 85},
                          {"label": "HR Compliance", "value": "100%", "w": 100},
                          {"label": "Employee Satisfaction", "value": "91%", "w": 91}
                        ]
                      },
                      {
                        "tag": "Intelligence",
                        "title": "Real-Time Business Intelligence & Reports",
                        "desc": "Access live dashboards, AI-powered insights, and executive-level reporting across every module. Make decisions backed by real data.",
                        "points": [
                          "Custom report builder",
                          "Predictive forecasting",
                          "Department-level drill-downs",
                          "Scheduled executive reports"
                        ],
                        "color": "#059669",
                        "bars": [
                          {"label": "Report Generation", "value": "Real-time", "w": 100},
                          {"label": "Data Accuracy", "value": "99.9%", "w": 99},
                          {"label": "Decision Speed", "value": "4x faster", "w": 80}
                        ]
                      }
                    ]
                    """;

            benefitsRepo.save(BenefitsSection.builder()
                    .badge("Platform Benefits")
                    .heading("Transform How Your Business")
                    .headingHighlight("Operates and Grows")
                    .benefitsJson(benefitsJson.trim())
                    .primaryColor("#1a56e8")
                    .isActive(true)
                    .build());
            log.info("✅ Benefits section initialized");
        }
    }

    // ════════════════════════════════════════════════════════════════
    //  7. TESTIMONIALS SECTION
    // ════════════════════════════════════════════════════════════════
    private void initTestimonials() {
        if (testimonialsRepo.count() == 0) {
            String testimonialsJson = """
                    [
                      {
                        "name": "James Richardson",
                        "role": "Chief Technology Officer",
                        "company": "Apex Manufacturing Group",
                        "review": "Implementing this ERP platform has been transformative. We unified 14 departments onto a single system, reduced reporting time by 70%, and eliminated three legacy tools. The ROI was visible within the first quarter.",
                        "avatar": "JR",
                        "color": "#1a56e8",
                        "rating": 5
                      },
                      {
                        "name": "Priya Anand",
                        "role": "VP of Human Resources",
                        "company": "NovaTech Solutions",
                        "review": "The HRMS module gave us complete visibility into our 2,000-person workforce. Onboarding went from 5 days to 1 day. Payroll accuracy is at 99.9%. I'd recommend this platform to any HR leader at scale.",
                        "avatar": "PA",
                        "color": "#7c3aed",
                        "rating": 5
                      },
                      {
                        "name": "Marcus Chen",
                        "role": "Director of Finance",
                        "company": "Greenfield Logistics",
                        "review": "The accounting and payroll modules are enterprise-grade. Financial consolidation across 8 entities now happens automatically. The audit trail capabilities have made compliance effortless.",
                        "avatar": "MC",
                        "color": "#059669",
                        "rating": 5
                      },
                      {
                        "name": "Sarah Williams",
                        "role": "COO",
                        "company": "Pinnacle Healthcare Network",
                        "review": "We needed a system that could handle complex healthcare operations — multi-site management, staff credentialing, and compliance. This platform delivered beyond our expectations.",
                        "avatar": "SW",
                        "color": "#dc2626",
                        "rating": 5
                      },
                      {
                        "name": "David Okonkwo",
                        "role": "Head of IT Infrastructure",
                        "company": "Sterling Retail Holdings",
                        "review": "The enterprise security features are outstanding. SOC 2 compliance, RBAC, and full audit logs gave our board the confidence to approve the platform company-wide. Support is exceptional.",
                        "avatar": "DO",
                        "color": "#d97706",
                        "rating": 5
                      },
                      {
                        "name": "Elena Vasquez",
                        "role": "CEO",
                        "company": "Meridian Construction Group",
                        "review": "Real-time project costing and workforce analytics have changed how we run projects. We can now forecast cash flow accurately and manage subcontractor compliance from a single dashboard.",
                        "avatar": "EV",
                        "color": "#0891b2",
                        "rating": 5
                      }
                    ]
                    """;

            testimonialsRepo.save(TestimonialsSection.builder()
                    .badge("Client Success Stories")
                    .heading("Trusted by Enterprise Leaders")
                    .headingHighlight("Around the World")
                    .testimonialsJson(testimonialsJson.trim())
                    .primaryColor("#1a56e8")
                    .isActive(true)
                    .build());
            log.info("✅ Testimonials section initialized");
        }
    }

    // ════════════════════════════════════════════════════════════════
    //  8. PRICING SECTION
    // ════════════════════════════════════════════════════════════════
    private void initPricing() {
        if (pricingRepo.count() == 0) {
            String plansJson = """
                    [
                      {
                        "name": "Professional",
                        "monthlyPrice": 299,
                        "yearlyPrice": 249,
                        "desc": "For growing businesses seeking structured ERP and HR capabilities.",
                        "features": [
                          "Up to 100 Employees",
                          "Core ERP Modules",
                          "HRMS & Payroll",
                          "Attendance Management",
                          "Standard Reports",
                          "Email Support",
                          "99.5% Uptime SLA",
                          "Mobile App Access"
                        ],
                        "notIncluded": [
                          "AI Analytics",
                          "Multi-Branch Management",
                          "Custom Workflows"
                        ],
                        "cta": "Get Started",
                        "featured": false,
                        "dark": false
                      },
                      {
                        "name": "Business",
                        "monthlyPrice": 699,
                        "yearlyPrice": 579,
                        "desc": "The complete platform for mid-market enterprises scaling operations.",
                        "features": [
                          "Up to 500 Employees",
                          "All ERP Modules",
                          "Advanced HRMS Suite",
                          "AI Analytics & Reports",
                          "CRM Integration",
                          "Inventory Management",
                          "Priority Support 24/7",
                          "99.9% Uptime SLA",
                          "Multi-Branch Management",
                          "Custom Workflows",
                          "Advanced API Access",
                          "SSO Integration"
                        ],
                        "notIncluded": [],
                        "cta": "Start with Business",
                        "featured": true,
                        "dark": false
                      },
                      {
                        "name": "Enterprise",
                        "monthlyPrice": null,
                        "yearlyPrice": null,
                        "desc": "Custom-built for large enterprises with complex operational requirements.",
                        "features": [
                          "Unlimited Employees",
                          "Custom Module Development",
                          "Dedicated Implementation",
                          "White-label Options",
                          "On-premise Deployment",
                          "SLA-backed 99.99% Uptime",
                          "Named Account Manager",
                          "Custom Integrations",
                          "Enterprise SSO",
                          "Compliance & Audit Support"
                        ],
                        "notIncluded": [],
                        "cta": "Contact Sales",
                        "featured": false,
                        "dark": true
                      }
                    ]
                    """;

            String trustBadgesJson = """
                    [
                      {"icon": "🔒", "label": "SOC 2 Certified"},
                      {"icon": "🌐", "label": "GDPR Compliant"},
                      {"icon": "⚡", "label": "14-Day Go-Live"}
                    ]
                    """;

            pricingRepo.save(PricingSection.builder()
                    .badge("Transparent Pricing")
                    .heading("Enterprise Pricing,")
                    .headingHighlight("No Hidden Costs")
                    .subheading("Choose the plan that fits your organisation. All plans include onboarding, training, and dedicated support.")
                    .yearlySaveText("Save 17%")
                    .footerNote("All plans include implementation support, dedicated onboarding, and SLA-backed uptime.")
                    .plansJson(plansJson.trim())
                    .trustBadgesJson(trustBadgesJson.trim())
                    .primaryColor("#1a56e8")
                    .isActive(true)
                    .build());
            log.info("✅ Pricing section initialized");
        }
    }

    // ════════════════════════════════════════════════════════════════
    //  9. FINAL CTA SECTION
    // ════════════════════════════════════════════════════════════════
    private void initFinalCta() {
        if (finalCtaRepo.count() == 0) {
            String statsJson = """
                    [
                      {"label": "Revenue", "value": "$2.4M"},
                      {"label": "Employees", "value": "1,284"},
                      {"label": "Payroll", "value": "$840K"},
                      {"label": "Uptime", "value": "99.9%"}
                    ]
                    """;

            String trustItemsJson = """
                    [
                      {"icon": "🔒", "label": "SOC 2 Certified"},
                      {"icon": "⚡", "label": "14-Day Go-Live"},
                      {"icon": "🌐", "label": "Global Support"}
                    ]
                    """;

            String floatingCardsJson = """
                    [
                      {"title": "Payroll", "subtitle": "Auto-Processed", "badge": "✓ 100% Accurate"},
                      {"title": "AI Insights", "subtitle": "3 New Alerts", "badge": "Just now"}
                    ]
                    """;

            finalCtaRepo.save(FinalCtaSection.builder()
                    .badge("Ready to Transform?")
                    .heading("Ready To Modernize\nYour Enterprise")
                    .headingHighlight("Operations?")
                    .subheading("Join 500+ enterprises that have streamlined operations, automated payroll, and gained real-time intelligence with our unified platform.")
                    .ctaPrimaryText("Book Free Demo")
                    .ctaSecondaryText("Contact Sales")
                    .statsJson(statsJson.trim())
                    .trustItemsJson(trustItemsJson.trim())
                    .floatingCardsJson(floatingCardsJson.trim())
                    .primaryColor("#1a56e8")
                    .isActive(true)
                    .build());
            log.info("✅ Final CTA section initialized");
        }
    }
}