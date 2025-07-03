
import { useState } from 'react';
import { Check, Star } from 'lucide-react';
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import { Button } from '@/components/ui/button';

const Subscription = () => {
  const [cardNumber, setCardNumber] = useState('1234 5678 9012 3456');
  const [expiryDate, setExpiryDate] = useState('MM/YY');
  const [cvv, setCvv] = useState('123');
  const [holderName, setHolderName] = useState('홍길동');

  const handleSubscribe = (e: React.FormEvent) => {
    e.preventDefault();
    alert('월 구독 신청이 완료되었습니다!');
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <Header />
      
      <main className="pt-24 pb-16">
        <div className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-12">
            <h1 className="text-3xl md:text-4xl font-light text-gray-900 mb-4">
              월 구독 신청
            </h1>
            <p className="text-lg text-gray-600">
              월 9,900원으로 모든 도서를 무제한으로 즐기세요
            </p>
          </div>
          
          <div className="grid lg:grid-cols-2 gap-12 max-w-5xl mx-auto">
            {/* 프리미엄 구독 */}
            <div className="bg-white rounded-lg border border-gray-200 p-8">
              <div className="text-center mb-8">
                <div className="w-16 h-16 bg-amber-100 rounded-full flex items-center justify-center mx-auto mb-4">
                  <div className="text-2xl">📚</div>
                </div>
                <h2 className="text-2xl font-light text-gray-900 mb-2">프리미엄 구독</h2>
                <div className="text-3xl font-light text-amber-700 mb-1">
                  ₩9,900<span className="text-lg text-gray-500">/월</span>
                </div>
              </div>
              
              <div className="space-y-4 mb-8">
                <div className="flex items-center gap-3">
                  <Check className="w-5 h-5 text-green-600" />
                  <span className="text-gray-700">플랫폼 내 모든 도서 무제한 열람</span>
                </div>
                <div className="flex items-center gap-3">
                  <Check className="w-5 h-5 text-green-600" />
                  <span className="text-gray-700">신작 도서 우선 열람 권한</span>
                </div>
                <div className="flex items-center gap-3">
                  <Check className="w-5 h-5 text-green-600" />
                  <span className="text-gray-700">광고 없는 깔끔한 읽기 환경</span>
                </div>
                <div className="flex items-center gap-3">
                  <Check className="w-5 h-5 text-green-600" />
                  <span className="text-gray-700">오프라인 다운로드 기능</span>
                </div>
                <div className="flex items-center gap-3">
                  <Check className="w-5 h-5 text-green-600" />
                  <span className="text-gray-700">개인 맞춤 추천 서비스</span>
                </div>
              </div>
              
              <div className="flex items-center gap-2 text-amber-600 mb-4">
                <Star className="w-5 h-5 fill-current" />
                <span className="font-medium">특별 혜택</span>
              </div>
              <p className="text-sm text-gray-600">
                첫 달 무료 체험! 언제든 해지 가능합니다.
              </p>
            </div>
            
            {/* 결제 정보 */}
            <div className="bg-white rounded-lg border border-gray-200 p-8">
              <h3 className="text-xl font-medium text-gray-900 mb-6">💳 결제 정보</h3>
              
              <form onSubmit={handleSubscribe}>
                <div className="space-y-6">
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">
                      카드 소유자명
                    </label>
                    <input
                      type="text"
                      value={holderName}
                      onChange={(e) => setHolderName(e.target.value)}
                      className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-transparent"
                    />
                  </div>
                  
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">
                      카드 번호
                    </label>
                    <input
                      type="text"
                      value={cardNumber}
                      onChange={(e) => setCardNumber(e.target.value)}
                      className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-transparent"
                    />
                  </div>
                  
                  <div className="grid grid-cols-2 gap-4">
                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-2">
                        만료일
                      </label>
                      <input
                        type="text"
                        value={expiryDate}
                        onChange={(e) => setExpiryDate(e.target.value)}
                        className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-transparent"
                      />
                    </div>
                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-2">
                        CVV
                      </label>
                      <input
                        type="text"
                        value={cvv}
                        onChange={(e) => setCvv(e.target.value)}
                        className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-transparent"
                      />
                    </div>
                  </div>
                </div>
                
                <Button 
                  type="submit"
                  className="w-full mt-8 bg-amber-700 hover:bg-amber-600 text-white py-4 text-lg"
                >
                  월 구독 신청하기
                </Button>
                
                <p className="text-xs text-gray-500 text-center mt-4">
                  구독 신청 시 이용약관 및 개인정보처리방침에 동의하게 됩니다.
                </p>
              </form>
            </div>
          </div>
        </div>
      </main>
      
      <Footer />
    </div>
  );
};

export default Subscription;
