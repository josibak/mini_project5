
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Button } from '@/components/ui/button';

const Hero = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // 컴포넌트 마운트 시 로그인 상태 확인
  useEffect(() => {
    const loginStatus = localStorage.getItem('isLoggedIn') === 'true';
    setIsLoggedIn(loginStatus);
  }, []);

  return (
    <section className="relative min-h-screen flex items-center justify-center bg-gradient-to-br from-warm-brown-50 to-warm-brown-100 overflow-hidden">
      {/* Background Pattern */}
      <div className="absolute inset-0 opacity-10">
        <div className="absolute top-20 left-10 w-32 h-32 bg-warm-brown-200 rounded-full"></div>
        <div className="absolute top-40 right-20 w-24 h-24 bg-warm-brown-300 rounded-full"></div>
        <div className="absolute bottom-32 left-1/4 w-20 h-20 bg-warm-brown-400 rounded-full"></div>
        <div className="absolute bottom-20 right-1/3 w-28 h-28 bg-warm-brown-500 rounded-full"></div>
      </div>
      
      <div className="relative z-10 max-w-6xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
        <div className="animate-fade-in">
          <p className="text-warm-brown-700 font-medium mb-4 tracking-wide">
            작가와 독자를 연결하는
          </p>
          
          <h1 className="text-5xl md:text-7xl font-light text-gray-900 mb-6 leading-tight">
            Iconic then.
            <br />
            <span className="text-warm-brown-700">Iconic now.</span>
          </h1>
          
          <p className="text-xl md:text-2xl text-gray-600 mb-8 max-w-3xl mx-auto leading-relaxed">
            창의적인 이야기와 함께 시작하는
            <br />
            새로운 독서 경험의 시작점
          </p>
          
          <div className="flex flex-col sm:flex-row gap-4 justify-center mb-16">
            <Link to="/subscription">
              <Button className="bg-warm-brown-700 hover:bg-warm-brown-800 text-white px-8 py-4 text-lg rounded-lg">
                ₩9,900원으로 모든 도서 읽기
              </Button>
            </Link>
            {!isLoggedIn && (
              <Link to="/login">
                <Button variant="outline" className="border-warm-brown-700 text-warm-brown-700 hover:bg-warm-brown-50 px-8 py-4 text-lg rounded-lg">
                  무료 체험
                </Button>
              </Link>
            )}
          </div>
          
          {/* Stats */}
          <div className="grid grid-cols-3 gap-8 max-w-2xl mx-auto">
            <div className="text-center">
              <div className="text-3xl font-light text-warm-brown-700 mb-2">4,800</div>
              <div className="text-gray-600">등록된 도서</div>
            </div>
            <div className="text-center">
              <div className="text-3xl font-light text-warm-brown-700 mb-2">850</div>
              <div className="text-gray-600">활동 작가</div>
            </div>
            <div className="text-center">
              <div className="text-3xl font-light text-warm-brown-700 mb-2">15,000</div>
              <div className="text-gray-600">구독 독자</div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Hero;
