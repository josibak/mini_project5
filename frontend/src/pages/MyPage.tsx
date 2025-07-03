
import { useState } from 'react';
import { User } from 'lucide-react';
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import { Button } from '@/components/ui/button';

const MyPage = () => {
  const [userInfo] = useState({
    name: '김독자',
    email: 'reader@example.com',
    joinDate: '2024-01-15',
    points: 1200
  });

  const [subscription] = useState({
    plan: '미구독',
    country: '미국'
  });

  return (
    <div className="min-h-screen bg-warm-brown-50">
      <Header />
      
      <main className="pt-24 pb-16">
        <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="mb-8">
            <h1 className="text-3xl md:text-4xl font-light text-gray-900 mb-4">
              마이페이지
            </h1>
            <p className="text-lg text-gray-600">
              회원 정보와 독서 활동을 확인하세요
            </p>
          </div>
          
          <div className="grid md:grid-cols-3 gap-8">
            {/* Left Panel - 회원 정보 */}
            <div className="md:col-span-2 space-y-8">
              <div className="bg-white rounded-lg border border-warm-brown-200 p-6">
                <div className="flex items-center gap-3 mb-6">
                  <User className="w-6 h-6 text-warm-brown-600" />
                  <h2 className="text-lg font-medium text-gray-900">회원 정보</h2>
                </div>
                
                <div className="grid md:grid-cols-2 gap-6">
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">이름</label>
                    <div className="text-gray-900">{userInfo.name}</div>
                  </div>
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">이메일</label>
                    <div className="text-gray-900">{userInfo.email}</div>
                  </div>
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">가입일</label>
                    <div className="text-gray-900">{userInfo.joinDate}</div>
                  </div>
                </div>
              </div>

              {/* 구독 정보 */}
              <div className="bg-white rounded-lg border border-warm-brown-200 p-6">
                <div className="flex items-center gap-3 mb-6">
                  <div className="w-6 h-6 text-warm-brown-600">📄</div>
                  <h2 className="text-lg font-medium text-gray-900">구독 정보</h2>
                </div>
                
                <div className="space-y-4">
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">구독 상태</label>
                    <div className="text-gray-900">{subscription.plan}</div>
                  </div>
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">국가</label>
                    <div className="text-gray-900 flex items-center gap-2">
                      <span>🇺🇸</span>
                      {subscription.country}
                    </div>
                  </div>
                </div>
                
                <Button 
                  className="w-full mt-6 bg-warm-brown-700 hover:bg-warm-brown-800 text-white"
                >
                  월 9,900원으로 무제한 독서하기
                </Button>
              </div>

              {/* KT 고객 혜택 */}
              <div className="bg-white rounded-lg border border-warm-brown-200 p-6">
                <div className="flex items-center gap-3 mb-6">
                  <div className="w-6 h-6 text-warm-brown-600">🔒</div>
                  <h2 className="text-lg font-medium text-gray-900">KT 고객 혜택</h2>
                </div>
                
                <div className="space-y-4">
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">KT 고객 인증</label>
                    <div className="text-gray-600">KT 고객 인증으로 추가 혜택을 받으세요</div>
                  </div>
                  
                  <div className="space-y-3">
                    <input
                      type="text"
                      placeholder="KT 고객 인증하기"
                      className="w-full px-4 py-3 border border-warm-brown-300 rounded-lg focus:ring-2 focus:ring-warm-brown-500 focus:border-transparent"
                    />
                    <div className="text-sm text-gray-500">
                      KT 고객 인증 시 500P 보너스 지급
                    </div>
                  </div>
                </div>
              </div>
            </div>

            {/* Right Panel - 포인트 */}
            <div className="space-y-6">
              <div className="bg-warm-brown-100 rounded-lg border border-warm-brown-300 p-6 text-center">
                <div className="flex items-center justify-center gap-2 mb-2">
                  <div className="w-6 h-6 text-warm-brown-700">💰</div>
                  <h3 className="text-lg font-medium text-warm-brown-800">포인트</h3>
                </div>
                
                <div className="text-3xl font-bold text-warm-brown-700 mb-2">
                  {userInfo.points.toLocaleString()}P
                </div>
                <div className="text-sm text-warm-brown-600 mb-4">
                  보유 포인트
                </div>
                
                <Button 
                  variant="outline" 
                  className="w-full border-warm-brown-400 text-warm-brown-700 hover:bg-warm-brown-200"
                >
                  포인트 충전하기
                </Button>
              </div>
            </div>
          </div>
        </div>
      </main>
      
      <Footer />
    </div>
  );
};

export default MyPage;
