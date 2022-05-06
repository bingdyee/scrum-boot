const journals = [
    {
        "issn": "1619-4500",
        "title": "4OR-A Quarterly Journal of Operations Research",
        "subjectArea": "Management",
        "reviewAverageDuration": "Quarterly",
        "openAccess": "Hybrid",
        "publisher": "Springer Berlin Heidelberg",
        "contact": "SPRINGER HEIDELBERG, TIERGARTENSTRASSE 17, HEIDELBERG, GERMANY, D-69121",
        "year": 2003,
        "country": "GERMANY"
    },
    {
        "issn": "0149-1423",
        "title": "AAPG BULLETIN",
        "subjectArea": "Medicine",
        "reviewAverageDuration": "Monthly",
        "openAccess": "No",
        "publisher": "American Association of Petroleum Geologists",
        "contact": "AMER ASSOC PETROLEUM GEOLOGIST, 1444 S BOULDER AVE, PO BOX 979, TULSA, USA, OK, 74119-3604",
        "year": 1974,
        "country": "UNITED STATES"
    },
    {
        "issn": "1550-7416",
        "title": "AAPS Journal",
        "subjectArea": "Medicine",
        "reviewAverageDuration": "Quarterly",
        "openAccess": "Hybrid",
        "publisher": "Springer International Publishing",
        "contact": "SPRINGER, 233 SPRING ST, NEW YORK , USA, NY, 10013",
        "year": 1999,
        "country": "UNITED STATES"
    },
    {
        "issn": "1530-9932",
        "title": "AAPS PHARMSCITECH",
        "subjectArea": "Medicine",
        "reviewAverageDuration": "Quarterly",
        "openAccess": "Hybrid",
        "publisher": "Springer International Publishing",
        "contact": "SPRINGER, 233 SPRING ST, NEW YORK , USA, NY, 10013",
        "year": 2000,
        "country": "UNITED STATES"
    },
    {
        "issn": "1532-8813",
        "title": "AATCC REVIEW",
        "subjectArea": "Engineering",
        "reviewAverageDuration": "Monthly",
        "openAccess": "No",
        "publisher": "American Association of Textile Chemists and Colorists",
        "contact": "AMER ASSOC PETROLEUM GEOLOGIST, 1444 S BOULDER AVE, PO BOX 979, TULSA, USA, OK, 74119-3604",
        "year": 2001,
        "country": "UNITED STATES"
    },
    {
        "issn": "0025-5858",
        "title": "ABHANDLUNGEN AUS DEM MATHEMATISCHEN SEMINAR DER UNIVERSITAT HAMBURG",
        "subjectArea": "Mathematics",
        "reviewAverageDuration": "Annual",
        "openAccess": "Hybrid",
        "publisher": "Springer Berlin Heidelberg",
        "contact": "SPRINGER HEIDELBERG, TIERGARTENSTRASSE 17, HEIDELBERG, GERMANY, D-69121",
        "year": 1922,
        "country": "GERMANY"
    },
    {
        "issn": "0001-4842",
        "title": "ACCOUNTS OF CHEMICAL RESEARCH",
        "subjectArea": "Chemistry",
        "reviewAverageDuration": "Monthly",
        "openAccess": "No",
        "publisher": "AMER CHEMICAL SOC",
        "contact": "AMER CHEMICAL SOC, 1155 16TH ST, NW, WASHINGTON, USA, DC, 20036",
        "year": 1968,
        "country": "UNITED STATES"
    },
    {
        "issn": "1549-6325",
        "title": "ACM Transactions on Algorithms",
        "subjectArea": "COMPUTER SCIENCE, THEORY & METHODS",
        "reviewAverageDuration": "Quarterly",
        "openAccess": "No",
        "publisher": "ASSOC COMPUTING MACHINERY",
        "contact": "ASSOC COMPUTING MACHINERY, 2 PENN PLAZA, STE 701, NEW YORK, USA, NY, 10121-0701",
        "year": 0,
        "country": "UNITED STATES"
    },
    {
        "issn": "0001-527X",
        "title": "ACTA BIOCHIMICA POLONICA",
        "subjectArea": "COMPUTER SCIENCE, THEORY & METHODS",
        "reviewAverageDuration": "Quarterly",
        "openAccess": "Yes",
        "publisher": "ACTA BIOCHIMICA POLONICA",
        "contact": "ACTA BIOCHIMICA POLONICA, PASTEURA 3, WARSAW, POLAND, 02-093",
        "year": 0,
        "country": "POLAND"
    },
    {
        "issn": "0195-6663",
        "title": "APPETITE",
        "subjectArea": "Medicine",
        "reviewAverageDuration": "Bimonthly",
        "openAccess": "No",
        "publisher": "CADEMIC PRESS LTD- ELSEVIER SCIENCE LTD",
        "contact": "ACADEMIC PRESS LTD- ELSEVIER SCIENCE LTD, 24-28 OVAL RD, LONDON, ENGLAND, NW1 7DX",
        "year": 1980,
        "country": "NETHERLANDS"
    }
];

export default [
    {
        url: '/v1/journal',
        type: 'get',
        response: (req: any) => {
            let content = (req.query.issn && journals.filter(j => j['issn'].includes(req.query.issn))) || journals;
            return {
                "code": 2000,
                "success": true,
                "data": {
                    "total": 57812,
                    "page": 0,
                    "size": 10,
                    "content": content
                }
            }
        }
    }
];