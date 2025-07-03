
import { BookOpen, Mail, Phone, MapPin } from 'lucide-react';

const Footer = () => {
  return (
    <footer className="bg-gray-50 border-t border-gray-200">
      <div className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
        <div className="grid md:grid-cols-2 gap-8">
          <div>
            <div className="flex items-center gap-2 mb-4">
              <BookOpen className="w-6 h-6 text-amber-700" />
              <span className="text-xl font-bold text-gray-900">StoryBloom</span>
            </div>
            <p className="text-gray-600 mb-4">작가와 독자를 연결하는</p>
            <p className="text-gray-600 mb-6">AI 기반 도서 출간 및 구독 플랫폼</p>
            
            <div className="space-y-3">
              <div className="flex items-center gap-3">
                <Mail className="w-5 h-5 text-gray-500" />
                <span className="text-gray-600">contact@storybloom.com</span>
              </div>
              <div className="flex items-center gap-3">
                <Phone className="w-5 h-5 text-gray-500" />
                <span className="text-gray-600">02-1234-5678</span>
              </div>
              <div className="flex items-center gap-3">
                <MapPin className="w-5 h-5 text-gray-500" />
                <span className="text-gray-600">서울특별시 강남구 테헤란로 123</span>
              </div>
            </div>
          </div>
          
          <div>
            <h3 className="font-medium text-gray-900 mb-4">문의하기</h3>
            <div className="space-y-2 text-gray-600">
              <p>궁금한 점이 있으시면</p>
              <p>언제든지 연락 주세요.</p>
              <p className="font-medium">빠르고 정확한 답변을 드리겠습니다.</p>
            </div>
          </div>
        </div>
        
        <div className="mt-8 pt-8 border-t border-gray-200 text-center">
          <p className="text-gray-600">
            © 2024 StoryBloom. 창의적인 이야기의 시작.
          </p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
