
import { useState } from 'react';
import { User, CheckCircle, XCircle, Eye, MessageSquare } from 'lucide-react';
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import { Button } from '@/components/ui/button';
import AdminAuthModal from '@/components/AdminAuthModal';

const Admin = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [showAuthModal, setShowAuthModal] = useState(true);
  const [selectedAuthor, setSelectedAuthor] = useState<any>(null);
  
  const [applications, setApplications] = useState([
    {
      id: 1,
      name: "김작가",
      email: "writer1@example.com",
      phone: "010-1234-5678",
      appliedDate: "2024-12-20",
      status: "대기중",
      bio: "안녕하세요. 10년간의 글쓰기 경험을 바탕으로 독자들에게 감동을 전하고 싶습니다. 주로 로맨스와 힐링 장르를 다루며, 일상의 소소한 행복을 글로 표현하는 것을 좋아합니다.",
      portfolio: "https://kim-writer.com",
      works: ["달빛 속의 약속", "카페에서 만난 사람"]
    },
    {
      id: 2,
      name: "박소설가",
      email: "novelist@example.com", 
      phone: "010-2345-6789",
      appliedDate: "2024-12-18",
      status: "대기중",
      bio: "판타지 장르를 전문으로 하는 작가입니다. 상상력이 풍부한 세계관과 매력적인 캐릭터로 독자들을 새로운 모험으로 안내하고 싶습니다. 웹소설 플랫폼에서 5년간 활동했습니다.",
      portfolio: "https://park-novelist.com",
      works: ["마법사의 귀환", "용의 전설"]
    },
    {
      id: 3,
      name: "이시인",
      email: "poet@example.com",
      phone: "010-3456-7890", 
      appliedDate: "2024-12-15",
      status: "승인됨",
      bio: "시와 에세이를 주로 작성하는 작가입니다.",
      portfolio: "https://lee-poet.com",
      works: ["봄날의 기억", "밤하늘의 별"]
    }
  ]);

  const handleApprove = (id: number) => {
    setApplications(prev => 
      prev.map(app => 
        app.id === id ? { ...app, status: "승인됨" } : app
      )
    );
  };

  const handleReject = (id: number) => {
    setApplications(prev => 
      prev.map(app => 
        app.id === id ? { ...app, status: "거절됨" } : app
      )
    );
  };

  const handleViewDetails = (author: any) => {
    setSelectedAuthor(author);
  };

  const getStatusBadge = (status: string) => {
    const statusConfig = {
      "대기중": { color: "bg-orange-100 text-orange-800", label: "대기중" },
      "승인됨": { color: "bg-green-100 text-green-800", label: "승인됨" },
      "거절됨": { color: "bg-red-100 text-red-800", label: "거절" }
    };
    
    const config = statusConfig[status as keyof typeof statusConfig];
    return (
      <span className={`inline-block px-3 py-1 text-sm rounded-full ${config.color}`}>
        {config.label}
      </span>
    );
  };

  const pendingCount = applications.filter(app => app.status === "대기중").length;

  if (!isAuthenticated) {
    return (
      <>
        <Header />
        <AdminAuthModal 
          isOpen={showAuthModal} 
          onClose={() => setShowAuthModal(false)}
          onAuthenticate={() => {
            setIsAuthenticated(true);
            setShowAuthModal(false);
          }}
        />
        <div className="min-h-screen bg-gray-50 pt-24">
          <div className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
            <h1 className="text-3xl font-light text-gray-900 mb-4">관리자 인증이 필요합니다</h1>
          </div>
        </div>
        <Footer />
      </>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50">
      <Header />
      
      <main className="pt-24 pb-16">
        <div className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="mb-8">
            <h1 className="text-3xl md:text-4xl font-light text-gray-900 mb-4">
              작가 등록 승인 관리
            </h1>
            <p className="text-lg text-gray-600">
              작가 등록 신청한 사용자들의 정보를 검토하고 승인 처리를 할 수 있습니다
            </p>
          </div>
          
          {/* Stats */}
          <div className="bg-warm-brown-50 border border-warm-brown-200 rounded-lg p-6 mb-8">
            <div className="flex items-center justify-between">
              <div>
                <h2 className="text-lg font-medium text-warm-brown-900 mb-2">승인 대기 중</h2>
                <p className="text-warm-brown-700">
                  현재 <span className="font-bold text-xl">{pendingCount}</span>명의 작가가 승인을 기다리고 있습니다.
                </p>
              </div>
              <div className="text-4xl text-warm-brown-600">📋</div>
            </div>
          </div>
          
          {/* Applications List */}
          <div className="space-y-6">
            {applications.map((application) => (
              <div key={application.id} className="bg-white rounded-lg border border-warm-brown-200 overflow-hidden">
                <div className="p-6">
                  <div className="flex items-start justify-between mb-4">
                    <div className="flex items-center gap-3">
                      <div className="w-12 h-12 bg-warm-brown-100 rounded-full flex items-center justify-center">
                        <User className="w-6 h-6 text-warm-brown-700" />
                      </div>
                      <div>
                        <h3 className="text-lg font-medium text-gray-900">{application.name}</h3>
                        <p className="text-sm text-gray-600">신청일: {application.appliedDate}</p>
                      </div>
                    </div>
                    {getStatusBadge(application.status)}
                  </div>
                  
                  <div className="grid md:grid-cols-2 gap-4 mb-4">
                    <div className="flex items-center gap-2">
                      <MessageSquare className="w-4 h-4 text-gray-400" />
                      <span className="text-sm text-gray-600">{application.email}</span>
                    </div>
                    <div className="flex items-center gap-2">
                      <span className="text-sm text-gray-600">📞 {application.phone}</span>
                    </div>
                  </div>
                  
                  <div className="mb-6">
                    <p className="text-gray-700 leading-relaxed">
                      {application.bio}
                    </p>
                  </div>
                  
                  <div className="flex gap-3">
                    <Button
                      variant="outline"
                      size="sm"
                      onClick={() => handleViewDetails(application)}
                      className="flex items-center gap-2 text-gray-600 hover:text-gray-900 border-warm-brown-300"
                    >
                      <Eye className="w-4 h-4" />
                      상세보기
                    </Button>
                    
                    {application.status === "대기중" && (
                      <>
                        <Button
                          onClick={() => handleApprove(application.id)}
                          size="sm"
                          className="bg-green-600 hover:bg-green-700 text-white flex items-center gap-2"
                        >
                          <CheckCircle className="w-4 h-4" />
                          승인
                        </Button>
                        <Button
                          onClick={() => handleReject(application.id)}
                          variant="outline"
                          size="sm"
                          className="text-red-600 border-red-200 hover:bg-red-50 flex items-center gap-2"
                        >
                          <XCircle className="w-4 h-4" />
                          거절
                        </Button>
                      </>
                    )}
                    
                    {application.status === "승인됨" && (
                      <div className="flex items-center gap-2 text-green-600">
                        <CheckCircle className="w-4 h-4" />
                        <span className="text-sm font-medium">승인 완료</span>
                      </div>
                    )}
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </main>
      
      {/* Author Details Modal */}
      {selectedAuthor && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4">
          <div className="bg-white rounded-lg max-w-2xl w-full max-h-[90vh] overflow-y-auto">
            <div className="p-6">
              <div className="flex items-center justify-between mb-6">
                <h2 className="text-2xl font-semibold text-gray-900">작가 상세 정보</h2>
                <button
                  onClick={() => setSelectedAuthor(null)}
                  className="text-gray-500 hover:text-gray-700"
                >
                  ✕
                </button>
              </div>
              
              <div className="space-y-6">
                <div className="flex items-center gap-4">
                  <div className="w-16 h-16 bg-warm-brown-100 rounded-full flex items-center justify-center">
                    <User className="w-8 h-8 text-warm-brown-700" />
                  </div>
                  <div>
                    <h3 className="text-xl font-medium text-gray-900">{selectedAuthor.name}</h3>
                    <p className="text-gray-600">{selectedAuthor.email}</p>
                  </div>
                </div>
                
                <div className="grid md:grid-cols-2 gap-6">
                  <div>
                    <h4 className="font-medium text-gray-900 mb-2">연락처</h4>
                    <p className="text-gray-600">{selectedAuthor.phone}</p>
                  </div>
                  <div>
                    <h4 className="font-medium text-gray-900 mb-2">신청일</h4>
                    <p className="text-gray-600">{selectedAuthor.appliedDate}</p>
                  </div>
                </div>
                
                <div>
                  <h4 className="font-medium text-gray-900 mb-2">자기소개</h4>
                  <p className="text-gray-600 leading-relaxed">{selectedAuthor.bio}</p>
                </div>
                
                <div>
                  <h4 className="font-medium text-gray-900 mb-2">포트폴리오</h4>
                  <a 
                    href={selectedAuthor.portfolio} 
                    target="_blank" 
                    rel="noopener noreferrer"
                    className="text-warm-brown-600 hover:text-warm-brown-700 underline"
                  >
                    {selectedAuthor.portfolio}
                  </a>
                </div>
                
                <div>
                  <h4 className="font-medium text-gray-900 mb-2">대표 작품</h4>
                  <ul className="space-y-1">
                    {selectedAuthor.works.map((work: string, index: number) => (
                      <li key={index} className="text-gray-600">• {work}</li>
                    ))}
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      )}
      
      <Footer />
    </div>
  );
};

export default Admin;
