
import { useState } from 'react';
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import { Button } from '@/components/ui/button';
import { Textarea } from '@/components/ui/textarea';
import { useToast } from '@/hooks/use-toast';

const AuthorRegistration = () => {
  const [isSubmitting, setIsSubmitting] = useState(false);
  const { toast } = useToast();
  
  const [formData, setFormData] = useState({
    name: '홍길동',
    email: 'author@example.com',
    bio: '작가로서의 경험, 글쓰기 스타일, 관심 분야 등을 자유롭게 작성해주세요.',
    portfolio: 'https://your-portfolio.com'
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    setIsSubmitting(true);
    
    toast({
      title: "작가 등록 신청되었습니다",
      description: "검토 후 연락드리겠습니다.",
      duration: 3000,
    });
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <Header />
      
      <main className="pt-24 pb-16">
        <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-12">
            <h1 className="text-3xl md:text-4xl font-light text-gray-900 mb-4">
              작가 등록 신청
            </h1>
            <p className="text-lg text-gray-600">
              StoryBloom의 작가가 되어 여러분의 이야기를 세상과 공유하세요
            </p>
          </div>
          
          <div className="bg-white rounded-lg shadow-sm border border-gray-200 overflow-hidden">
            <div className="p-8">
              <form onSubmit={handleSubmit} className="space-y-8">
                <div className="space-y-6">
                  <h2 className="text-xl font-medium text-gray-900 flex items-center gap-2">
                    👤 작가 정보
                  </h2>
                  
                  <div>
                    <label htmlFor="name" className="block text-sm font-medium text-gray-700 mb-2">
                      이름 *
                    </label>
                    <input
                      type="text"
                      id="name"
                      value={formData.name}
                      onChange={(e) => setFormData({...formData, name: e.target.value})}
                      className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-transparent"
                      placeholder="실제 이름을 입력하세요"
                      disabled={isSubmitting}
                    />
                  </div>
                  
                  <div>
                    <label htmlFor="email" className="block text-sm font-medium text-gray-700 mb-2">
                      이메일 *
                    </label>
                    <input
                      type="email"
                      id="email"
                      value={formData.email}
                      onChange={(e) => setFormData({...formData, email: e.target.value})}
                      className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-transparent"
                      placeholder="연락 가능한 이메일 주소"
                      disabled={isSubmitting}
                    />
                  </div>
                  
                  <div className="text-sm text-gray-500 bg-gray-50 p-4 rounded-lg">
                    <p><strong>상태:</strong> 임시저장</p>
                    <p><strong>글자 수:</strong> 0자</p>
                  </div>
                </div>
                
                <div className="space-y-6">
                  <h2 className="text-xl font-medium text-gray-900">
                    본문 작성
                  </h2>
                  
                  <div>
                    <Textarea
                      value={formData.bio}
                      onChange={(e) => setFormData({...formData, bio: e.target.value})}
                      placeholder="여기에 작품을 작성해주세요."
                      rows={10}
                      className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-transparent resize-none"
                      disabled={isSubmitting}
                    />
                  </div>
                  
                  <div className="bg-gray-50 p-4 rounded-lg">
                    <h3 className="font-medium text-gray-900 mb-2">팁:</h3>
                    <ul className="text-sm text-gray-600 space-y-1">
                      <li>- 단락 구분을 명확히 해주세요</li>
                      <li>- 대화는 따옴표로 구분해주세요</li>
                      <li>- 최소 1,000자 이상 작성해야 최종저장이 가능합니다</li>
                    </ul>
                  </div>
                </div>
                
                <div className="space-y-6">
                  <h2 className="text-xl font-medium text-gray-900">
                    포트폴리오 URL
                  </h2>
                  
                  <div>
                    <input
                      type="url"
                      value={formData.portfolio}
                      onChange={(e) => setFormData({...formData, portfolio: e.target.value})}
                      className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-transparent"
                      placeholder="https://your-portfolio.com"
                      disabled={isSubmitting}
                    />
                  </div>
                </div>
                
                <div className="flex justify-end">
                  <Button 
                    type="submit" 
                    className="bg-amber-700 hover:bg-amber-600 text-white px-8 py-3"
                    disabled={isSubmitting}
                  >
                    {isSubmitting ? '신청 처리 중...' : '등록 신청'}
                  </Button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </main>
      
      <Footer />
    </div>
  );
};

export default AuthorRegistration;
