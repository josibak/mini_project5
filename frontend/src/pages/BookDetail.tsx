
import { useState } from 'react';
import { Eye } from 'lucide-react';
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import { Button } from '@/components/ui/button';

const BookDetail = () => {
  const [isSubscribed, setIsSubscribed] = useState(false);

  const handleSubscribe = () => {
    setIsSubscribed(true);
    alert('구독이 시작되었습니다!');
  };

  const handleTrial = () => {
    alert('무료 체험 페이지로 이동합니다.');
  };

  return (
    <div className="min-h-screen bg-white">
      <Header />
      
      <main className="pt-24 pb-16">
        <div className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid lg:grid-cols-2 gap-12">
            {/* 책 이미지 */}
            <div className="flex justify-center">
              <div className="max-w-md">
                <img 
                  src="https://images.unsplash.com/photo-1544947950-fa07a98d237f?w=400&h=600&fit=crop"
                  alt="달빛 아래의 서약"
                  className="w-full rounded-lg shadow-lg"
                />
                <div className="text-center mt-4 text-gray-600 flex items-center justify-center gap-2">
                  <Eye className="w-4 h-4" />
                  <span>127</span>
                </div>
                
                <div className="mt-6 space-y-3">
                  <Button 
                    onClick={handleSubscribe}
                    className="w-full bg-amber-700 hover:bg-amber-600 text-white py-3"
                  >
                    월 9,900원으로 무제한 열람하기
                  </Button>
                  <Button 
                    onClick={handleTrial}
                    variant="outline"
                    className="w-full py-3"
                  >
                    무인본을 열람하기 (300P)
                  </Button>
                </div>
              </div>
            </div>
            
            {/* 책 정보 */}
            <div>
              <div className="mb-4">
                <span className="inline-block px-3 py-1 bg-amber-100 text-amber-800 text-sm rounded-full">
                  로맨스
                </span>
              </div>
              
              <h1 className="text-3xl md:text-4xl font-light text-gray-900 mb-4">
                달빛 아래의 서약
              </h1>
              
              <div className="flex items-center gap-2 text-gray-600 mb-8">
                <div className="w-6 h-6 bg-gray-200 rounded-full"></div>
                <span>김소연</span>
              </div>
              
              <div className="mb-8">
                <h2 className="text-xl font-medium text-gray-900 mb-4">시 요약본</h2>
                <p className="text-gray-700 leading-relaxed">
                  두 영혼이 만나 펼치지는 아름다운 사랑 이야기. 달빛이 비추는 밤, 운명적으로 만난 두 사람의 애틋한 감정이 섬세하게 그려진 작품입니다. 사랑의 진정한 의미와 희생의 아름다움을 되새기게 하는 감동적인 스토리가 독자들의 마음을 사로잡습니다.
                </p>
              </div>
            </div>
          </div>
        </div>
      </main>
      
      <Footer />
    </div>
  );
};

export default BookDetail;
